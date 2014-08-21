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
package ds2.oss.core.api.xmpp;

/**
 * Basic XMPP support.
 *
 * @author dstrauss
 * @version 0.3
 */
public interface IXmppSupport {

    /**
     * Tells you if the connection to the xmpp server is alive.
     *
     * @return true if so, otherwise and by default false
     */
    boolean isConnected();

    /**
     * Allows the given jid to follow you.
     *
     * @param jid the jid that has requested to follow you via a subscription
     * presence request
     */
    void sendPresenseSubscribedTo(String jid);

    /**
     * Sends a plain message to the given jid.
     *
     * @param jid the jid to write to
     * @param msg the message
     */
    void sendPlainTextMessage(String jid, String msg);

    /**
     * Sends the given object. It is considered that the given object is of type
     * Packet.
     *
     * @param o the packet to send
     */
    void sendPacket(Object o);
}
