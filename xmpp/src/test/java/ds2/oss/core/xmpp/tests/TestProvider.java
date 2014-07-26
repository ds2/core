/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.xmpp.tests;

import ds2.oss.core.api.SecurityBaseData;
import ds2.oss.core.api.xmpp.IXmppConnectionData;
import ds2.oss.core.api.xmpp.XmppConnectData;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 *
 * @author dstrauss
 */
public class TestProvider {

    @Produces
    @ApplicationScoped
    public IXmppConnectionData getConnData() {
        XmppConnectData rc = new XmppConnectData();
        rc.setPassword("test");
        rc.setRosterLoadedAtLogin(true);
        rc.setSendPresence(true);
        rc.setUsername("localtest");
        rc.setServerHostname("localhost");
        rc.setIgnoreSslTrustErrors(true);
        return rc;
    }
}
