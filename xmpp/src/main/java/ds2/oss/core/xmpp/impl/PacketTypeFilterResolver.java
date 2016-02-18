/*
 * Copyright 2012-2015 Dirk Strauss
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
package ds2.oss.core.xmpp.impl;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

import ds2.oss.core.api.xmpp.PacketTypes;
import ds2.oss.core.api.xmpp.RequireSecurity;

/**
 *
 * @author dstrauss
 */
public class PacketTypeFilterResolver {
    
    /**
     * Resolves the given packet type into a smack packet type filter.
     *
     * @param t
     *            the packet type
     * @return the filter to use
     */
    PacketTypeFilter resolve(PacketTypes t) {
        Class<? extends Packet> p = Message.class;
        switch (t) {
            case Message:
                p = Message.class;
                break;
            default:
                // nothing to do
                break;
        }
        return new PacketTypeFilter(p);
    }
    
    /**
     * Resolves the given guideline into a smack guideline.
     * 
     * @param s
     *            the security guide line
     * @return the related smack guideline
     */
    ConnectionConfiguration.SecurityMode resolve(RequireSecurity s) {
        switch (s) {
            case no:
                return ConnectionConfiguration.SecurityMode.disabled;
            case required:
                return ConnectionConfiguration.SecurityMode.required;
            default:
                return ConnectionConfiguration.SecurityMode.enabled;
        }
    }
}
