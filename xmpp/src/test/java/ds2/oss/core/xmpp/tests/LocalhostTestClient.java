/*
 * Copyright 2012-2014 Dirk Strauss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.xmpp.tests;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.xmpp.IXmppSupport;
import ds2.oss.core.testutils.AbstractInjectionEnvironment;

/**
 * This client here tests the connection to another xmpp server on localhost, checking if it works.
 *
 * @author dstrauss
 * @version 0.3
 */
public class LocalhostTestClient extends AbstractInjectionEnvironment {
    
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    public static void main(String... params) {
        LocalhostTestClient t = new LocalhostTestClient();
        t.onSuiteStart();
        LOG.info("Getting xmpp");
        IXmppSupport xmpp = LocalhostTestClient.getInstance(IXmppSupport.class);
        LOG.info("Is connected: {}", xmpp.isConnected());
        if (xmpp.isConnected()) {
            try {
                Thread.sleep(1000 * 60 * 60);
            } catch (InterruptedException ex) {
                LOG.error("Error when waiting!", ex);
            }
        }
        t.onSuiteEnd();
    }
}
