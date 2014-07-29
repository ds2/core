/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.xmpp.gcm;

/**
 *
 * @author dstrauss
 */
public interface GcmActionListener {

    void onUpstreamMessage(String json, GcmDownstreamMessage m);

    void onAckMessage(String json);

    void onNackMessage(String json);

    void onControlMessage(String json);
}
