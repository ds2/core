/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.xmpp.api;

/**
 *
 * @author dstrauss
 */
public interface XmppActionsListener {

    /**
     * Called when a given user requests a subscribe.
     *
     * @param jid the user who wants to subscribe to you
     */
    void onSubscribe(String jid);

    void onMessageReceived(String from, String msg);
}
