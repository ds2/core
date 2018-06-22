/*
 * Copyright 2018 DS/2 <dstrauss@ds-2.de>
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
        //xmpp.sendPlainTextMessage(from, "Nachricht erhalten: " + msg);
    }

}
