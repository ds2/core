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
package ds2.oss.core.xmpp.gcm;

import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.xmlpull.v1.XmlPullParser;

import ds2.oss.core.api.annotations.SmackPEProvider;

/**
 * Taken from the android gcm tutorial, this extension provider registers any incoming package with
 * a specific namespace to be handled with this extension. It will basically find the first text
 * node within the received package, and expects this node to be a json document. This json document
 * is given and returned as its packet extension content.
 *
 * @author unknown
 * @version 0.3
 */
@SmackPEProvider(elementName = "gcm", namespace = "google:mobile:data")
public class GcmPacketExtensionProvider implements PacketExtensionProvider {
    
    @Override
    public PacketExtension parseExtension(XmlPullParser parser) throws Exception {
        String json = parser.nextText();
        return new GcmPacketExtension(json);
    }
    
}
