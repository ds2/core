/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.xmpp.tests;

import ds2.oss.core.api.xmpp.IXmppSupport;
import ds2.oss.core.api.xmpp.XmppActionsListener;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dstrauss
 */
@ApplicationScoped
public class ActionsListener implements XmppActionsListener {

    private static final Logger LOG = LoggerFactory.getLogger(ActionsListener.class);

    @Inject
    private IXmppSupport xmpp;

    @Override
    public void onSubscribe(String jid) {
        xmpp.sendPresenseSubscribedTo(jid);
    }

    @Override
    public void onMessageReceived(String from, String msg) {
        xmpp.sendPlainMessage(from, "Nachricht erhalten: " + msg);
    }

}
