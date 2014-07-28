/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.xmpp.gcm;

import ds2.oss.core.api.annotations.SmackPacketListener;
import ds2.oss.core.api.xmpp.PacketTypes;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

/**
 * A packet listener for Google Cloud Messaging messages.
 *
 * @author dstrauss
 * @version 0.3
 */
@SmackPacketListener(type = PacketTypes.Message)
public class GcmPacketListener implements PacketListener {

    @Override
    public void processPacket(Packet packet) throws SmackException.NotConnectedException {
        //check if packet is for us
        Message m = (Message) packet;
        GcmPacketExtension extension = (GcmPacketExtension) m.getExtension(GcmPacketExtension.GCM_NAMESPACE);
        if (extension == null) {
            return;
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
