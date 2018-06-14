/*
 * Copyright 2018 DS/2 <dstrauss@ds-2.de>
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
package ds2.oss.core.xmpp.gcm;

import javax.inject.Inject;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.JsonCodec;
import ds2.oss.core.api.annotations.SmackPacketListener;
import ds2.oss.core.api.xmpp.PacketTypes;

/**
 * A packet listener for Google Cloud Messaging messages.
 *
 * @author dstrauss
 * @version 0.3
 */
@SmackPacketListener(type = PacketTypes.Message)
public class GcmPacketListener implements PacketListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(GcmPacketListener.class);
    
    @Inject
    private JsonCodec codec;
    @Inject
    private GcmActionListener actions;
    @Inject
    private IGcmSupport gcmSender;
    
    @Override
    public void processPacket(Packet packet) throws SmackException.NotConnectedException {
        // check if packet is for us
        Message m = (Message) packet;
        GcmPacketExtension extension = (GcmPacketExtension) m.getExtension(GcmPacketExtension.GCM_NAMESPACE);
        if (extension == null) {
            // No gcm tag found
            return;
        }
        String json = extension.getJson();
        LOG.debug("json received is: {}", json);
        try {
            BaseJsonContent obj = codec.decode(json, BaseJsonContent.class);
            if (obj.getMessageType() == null) {
                actions.onUpstreamMessage(json, codec.decode(json, GcmDownstreamMessage.class));
                sendAck(obj.getFrom(), obj.getMessageId());
            } else if ("ack".equalsIgnoreCase(obj.getMessageType())) {
                actions.onAckMessage(json);
            } else if ("nack".equalsIgnoreCase(obj.getMessageType())) {
                actions.onNackMessage(json);
            } else if ("control".equalsIgnoreCase(obj.getMessageType())) {
                actions.onControlMessage(json);
            } else {
                LOG.warn("Unknown message type: {}", obj.getMessageType());
            }
        } catch (CoreException ex) {
            LOG.warn("Error when decoding the given json!", ex);
        }
    }
    
    private void sendAck(String from, String mesageId) {
        BaseJsonContent b = new BaseJsonContent();
        b.setTo(from);
        b.setMessageId(mesageId);
        b.setMessageType("ack");
        gcmSender.sendMessage(b);
    }
    
}
