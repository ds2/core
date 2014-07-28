/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.xmpp.gcm;

/**
 * Contract to send google cloud messaging data.
 *
 * @author dstrauss
 * @version 0.3
 */
public interface IGcmSupport {

    /**
     * Sends a message to gcm.
     *
     * @param m the message
     */
    <E extends BaseJsonContent> void sendMessage(E m);
}
