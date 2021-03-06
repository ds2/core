/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package ds2.oss.core.xmpp.impl;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.PacketInterceptor;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smack.sasl.SASLMechanism.Response;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.ITrustingSslSocketFactoryProvider;
import ds2.oss.core.api.annotations.SmackPEProvider;
import ds2.oss.core.api.annotations.SmackPacketListener;
import ds2.oss.core.api.xmpp.IPacketIdProvider;
import ds2.oss.core.api.xmpp.IXmppConnectionData;
import ds2.oss.core.api.xmpp.IXmppSupport;
import ds2.oss.core.api.xmpp.XmppActionsListener;

/**
 *
 * @author dstrauss
 */
@ApplicationScoped
public class XmppSupport implements IXmppSupport {
    
    /**
     * A logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    /**
     * The connect data.
     */
    @Inject
    private IXmppConnectionData connectData;
    /**
     * The xmpp connection to the server.
     */
    private XMPPConnection conn;
    /**
     * Flag to indicate if we can send messages.
     */
    private volatile boolean connected = false;
    @Inject
    private ITrustingSslSocketFactoryProvider sslProv;
    @Inject
    private XmppActionsListener actionsListener;
    @Inject
    @Any
    @SmackPEProvider
    private Instance<PacketExtensionProvider> packetExtensions;
    @Inject
    @Any
    @SmackPacketListener
    private Instance<PacketListener> packetListeners;
    @Inject
    private PacketTypeFilterResolver typeResolver;
    @Inject
    private IPacketIdProvider numGenerator;
    
    @PostConstruct
    public void onLoad() {
        if (!packetExtensions.isUnsatisfied()) {
            LOG.debug("Adding some providers");
            for (PacketExtensionProvider p : packetExtensions) {
                SmackPEProvider anno2 = p.getClass().getAnnotation(SmackPEProvider.class);
                if (anno2 == null) {
                    LOG.debug("Found provider {} has no annotation, ignoring it", p.getClass());
                    continue;
                }
                LOG.info("Adding found provider {}", p.getClass());
                ProviderManager.addExtensionProvider(anno2.elementName(), anno2.namespace(), p);
            }
        }
        
        LOG.info("Starting XMPP connection");
        try {
            ConnectionConfiguration config =
                new ConnectionConfiguration(connectData.getServerHostname(), connectData.getServerPort());
            ConnectionConfiguration.SecurityMode sec = typeResolver.resolve(connectData.getSecurityLevel());
            config.setSecurityMode(sec);
            config.setReconnectionAllowed(true);
            config.setRosterLoadedAtLogin(connectData.isRosterLoadedAtLogin());
            config.setSendPresence(connectData.isSendPresence());
            config.setSocketFactory(sslProv.getTrustingFactory(connectData.isIgnoreSslTrustErrors()));
            config.setDebuggerEnabled(connectData.isDebuggerEnabled());
            
            conn = new XMPPTCPConnection(config);
            LOG.debug(
                "Perform connect to {}:{} as {}...",
                new Object[] { connectData.getServerHostname(), connectData.getServerPort(), connectData.getUsername() });
            conn.connect();
            LOG.debug("Adding common listeners");
            conn.addConnectionListener(new ConnectionListener() {
                
                @Override
                public void connected(XMPPConnection xmppc) {
                    LOG.info("is connected now");
                    connected = true;
                }
                
                @Override
                public void authenticated(XMPPConnection xmppc) {
                    LOG.debug("is authenticated");
                    connected = true;
                }
                
                @Override
                public void connectionClosed() {
                    LOG.debug("connection is closed");
                    connected = false;
                }
                
                @Override
                public void connectionClosedOnError(Exception excptn) {
                    LOG.debug("Connection has been closed!", excptn);
                    connected = false;
                }
                
                @Override
                public void reconnectingIn(int i) {
                    LOG.debug("perform reconnect in {} seconds", i);
                    connected = false;
                }
                
                @Override
                public void reconnectionSuccessful() {
                    LOG.debug("reconnect successful");
                    connected = true;
                }
                
                @Override
                public void reconnectionFailed(Exception excptn) {
                    LOG.error("Reconnect failed.", excptn);
                    connected = false;
                }
            });
            LOG.debug("Adding common packet listeners");
            conn.addPacketListener(new PacketListener() {
                
                @Override
                public void processPacket(Packet packet) throws SmackException.NotConnectedException {
                    LOG.debug("Got message packet: {}", packet);
                    Message m = (Message) packet;
                    if (m.getBody() != null) {
                        actionsListener.onMessageReceived(m.getFrom(), m.getBody());
                    }
                }
            }, new PacketTypeFilter(Message.class));
            conn.addPacketListener(new PacketListener() {
                
                @Override
                public void processPacket(Packet packet) throws SmackException.NotConnectedException {
                    LOG.debug("Presence packet: {}", packet.toXML());
                    Presence p = (Presence) packet;
                    if (Presence.Type.subscribe.equals(p.getType())) {
                        actionsListener.onSubscribe(p.getFrom());
                    }
                }
            }, new PacketTypeFilter(Presence.class));
            conn.addPacketListener(new PacketListener() {
                
                @Override
                public void processPacket(Packet packet) throws SmackException.NotConnectedException {
                    LOG.debug("Response packet: {}", packet);
                }
            }, new PacketTypeFilter(Response.class));
            
            if (!packetListeners.isUnsatisfied()) {
                for (PacketListener l : packetListeners) {
                    SmackPacketListener anno = l.getClass().getAnnotation(SmackPacketListener.class);
                    if (anno == null) {
                        continue;
                    }
                    LOG.info("Adding found listener {}", l.getClass());
                    conn.addPacketListener(l, typeResolver.resolve(anno.type()));
                }
            }
            
            LOG.debug("Adding interceptors");
            conn.addPacketInterceptor(new PacketInterceptor() {
                
                @Override
                public void interceptPacket(Packet packet) {
                    LOG.debug("Sending message: {}", packet.toXML());
                }
            }, new PacketTypeFilter(Message.class));
            
            LOG.debug("Performing login...");
            if (connectData.getUsername() != null) {
                conn.login(connectData.getUsername(), connectData.getPassword());
            } else {
                conn.loginAnonymously();
            }
            LOG.debug("The callbacks should now tell if we are connected");
        } catch (SmackException | IOException | XMPPException ex) {
            LOG.error("Error when connecting!", ex);
        }
    }
    
    @PreDestroy
    public void onEnd() {
        if (conn != null) {
            LOG.debug("Disconnecting now.");
            try {
                conn.disconnect();
            } catch (SmackException.NotConnectedException ex) {
                LOG.debug("Error on disconnect.", ex);
            }
        }
        LOG.debug("Finished");
    }
    
    private void sendPacket(Packet packet) {
        if (connected) {
            try {
                if (packet.getPacketID() == null) {
                    packet.setPacketID(numGenerator.getNextId());
                }
                conn.sendPacket(packet);
            } catch (SmackException.NotConnectedException ex) {
                connected = false;
                LOG.debug("Error when sending the packet!", ex);
            }
        } else {
            LOG.debug("Sending cancelled, not connected!");
        }
    }
    
    @Override
    public boolean isConnected() {
        return connected;
    }
    
    @Override
    public void sendPresenseSubscribedTo(String jid) {
        if (connected) {
            Presence p = new Presence(Presence.Type.subscribed);
            p.setTo(jid);
            sendPacket(p);
        }
    }
    
    @Override
    public void sendPlainTextMessage(String jid, String msg) {
        Message m = new Message(jid, Message.Type.chat);
        m.setFrom(conn.getUser());
        m.setBody(msg);
        sendPacket(m);
    }
    
    @Override
    public void sendPacket(Object o) {
        if (o == null || !(o instanceof Packet)) {
            LOG.warn("Given object is not a packet, ignoring.");
            return;
        }
        sendPacket((Packet) o);
    }
    
}
