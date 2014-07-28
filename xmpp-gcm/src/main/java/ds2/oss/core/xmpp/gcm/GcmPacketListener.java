/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.xmpp.gcm;

import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.JsonCodec;
import ds2.oss.core.api.annotations.SmackPacketListener;
import ds2.oss.core.api.xmpp.IXmppSupport;
import ds2.oss.core.api.xmpp.PacketTypes;
import javax.inject.Inject;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        //check if packet is for us
        Message m = (Message) packet;
        GcmPacketExtension extension = (GcmPacketExtension) m.getExtension(GcmPacketExtension.GCM_NAMESPACE);
        if (extension == null) {
            //No gcm tag found
            return;
        }
        String json = extension.getJson();
        LOG.debug("json received is: {}", json);
        try {
            BaseJsonContent obj = codec.decode(json, BaseJsonContent.class);
            if (obj.getMessageType() == null) {
                actions.onUpstreamMessage(json);
                sendAck(obj.getFrom(), obj.getMesageId());
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
        b.setMesageId(mesageId);
        b.setMessageType("ack");
        gcmSender.sendMessage(b);
    }

}
