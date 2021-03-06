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
package ds2.oss.core.api.xmpp;

/**
 * The actions listener. This contract deals with incoming XMPP messages.
 *
 * @author dstrauss
 * @version 0.3
 */
public interface XmppActionsListener {

    /**
     * When a message has been received.
     *
     * @param from
     *            the jid from the sender
     * @param msg
     *            the textual message content
     */
    void onMessageReceived(String from, String msg);

    /**
     * Called when a given user requests a subscribe.
     *
     * @param jid
     *            the user who wants to subscribe to you
     */
    void onSubscribe(String jid);
}
