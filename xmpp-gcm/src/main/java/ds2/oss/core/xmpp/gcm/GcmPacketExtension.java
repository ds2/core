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
package ds2.oss.core.xmpp.gcm;

import org.jivesoftware.smack.packet.DefaultPacketExtension;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.util.StringUtils;

/**
 * Google Cloud Messaging packet extension. Taken from the android developer website.
 *
 * @author unknown
 * @version 0.3
 */
public class GcmPacketExtension extends DefaultPacketExtension {
    
    public static final String GCM_ELEMENT_NAME = "gcm";
    public static final String GCM_NAMESPACE = "google:mobile:data";
    
    /**
     * The json content.
     */
    private final String json;
    
    /**
     * Inits the packet with the given content.
     *
     * @param json
     *            the json content
     */
    public GcmPacketExtension(String json) {
        super(GCM_ELEMENT_NAME, GCM_NAMESPACE);
        this.json = json;
    }
    
    /**
     * Returns the json content.
     *
     * @return the json content
     */
    public String getJson() {
        return json;
    }
    
    /**
     * Converts this object into an xml representation.
     *
     * @return the xml version of this packet.
     */
    @Override
    public String toXML() {
        return String.format("<%s xmlns=\"%s\">%s</%s>",
            GCM_ELEMENT_NAME, GCM_NAMESPACE,
            StringUtils.escapeForXML(json), GCM_ELEMENT_NAME);
    }
    
    /**
     * Converts this packet into a message packet to be sent to the server.
     *
     * @return the message packet
     */
    public Packet toPacket() {
        Message message = new Message();
        message.addExtension(this);
        return message;
    }
}
