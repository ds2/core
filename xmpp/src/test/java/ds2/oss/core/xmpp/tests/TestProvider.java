/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
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
        rc.setDebuggerEnabled(true);
        rc.setSecurityLevel(RequireSecurity.optional);
        return rc;
    }
}
