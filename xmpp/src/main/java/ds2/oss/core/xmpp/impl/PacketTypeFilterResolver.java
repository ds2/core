/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.xmpp.impl;

import ds2.oss.core.api.xmpp.PacketTypes;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

/**
 *
 * @author dstrauss
 */
public class PacketTypeFilterResolver {

    /**
     * Resolves the given packet type into a smack packet type filter.
     *
     * @param t the packet type
     * @return the filter to use
     */
    PacketTypeFilter resolve(PacketTypes t) {
        Class<? extends Packet> p = Message.class;
        switch (t) {
            case Message:
                p = Message.class;
                break;
            default:
                //nothing to do
                break;
        }
        return new PacketTypeFilter(p);
    }
}
