/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.api.xmpp;

import java.io.Serializable;

/**
 *
 * @author dstrauss
 */
public interface IXmppConnectionData extends Serializable {

    String getServerHostname();

    int getServerPort();

    String getUsername();

    String getPassword();

    boolean isSendPresence();

    boolean isRosterLoadedAtLogin();

    boolean isIgnoreSslTrustErrors();
}
