/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.xmpp.tests;

import ds2.oss.core.api.IoService;
import ds2.oss.core.api.xmpp.IXmppConnectionData;
import ds2.oss.core.api.xmpp.RequireSecurity;
import ds2.oss.core.api.xmpp.XmppConnectData;
import java.util.Properties;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 *
 * @author dstrauss
 */
public class TestProvider {

    @Inject
    private IoService io;

    @Produces
    @ApplicationScoped
    public IXmppConnectionData getConnData() {
        XmppConnectData rc = new XmppConnectData();
        rc.setRosterLoadedAtLogin(false);
        rc.setSendPresence(false);
        Properties props = io.loadProperties("/google-authdata.properties");
        rc.setUsername(props.getProperty("google.projectId") + "@gcm.googleapis.com");
        rc.setPassword(props.getProperty("google.authKey"));
        rc.setServerHostname("gcm-staging.googleapis.com");
        rc.setServerPort(5236);
        rc.setIgnoreSslTrustErrors(true);
        rc.setSecurityLevel(RequireSecurity.optional);
        return rc;
    }
}
