/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.xmpp.tests;

import ds2.oss.core.api.xmpp.IXmppSupport;
import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This client here tests the connection to another xmpp server on localhost,
 * checking if it works.
 *
 * @author dstrauss
 */
public class LocalhostTestClient extends AbstractInjectionEnvironment {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String... params) {
        LocalhostTestClient t = new LocalhostTestClient();
        t.onSuite();
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
