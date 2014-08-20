/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.api.xmpp;

/**
 * Basic XMPP support.
 *
 * @author dstrauss
 * @version 0.3
 */
public interface IXmppSupport {

    /**
     * Tells you if the connection to the xmpp server is alive.
     *
     * @return true if so, otherwise and by default false
     */
    boolean isConnected();

    /**
     * Allows the given jid to follow you.
     *
     * @param jid the jid that has requested to follow you via a subscription
     * presence request
     */
    void sendPresenseSubscribedTo(String jid);

    /**
     * Sends a plain message to the given jid.
     *
     * @param jid the jid to write to
     * @param msg the message
     */
    void sendPlainTextMessage(String jid, String msg);

    /**
     * Sends the given object. It is considered that the given object is of type
     * Packet.
     *
     * @param o the packet to send
     */
    void sendPacket(Object o);
}
