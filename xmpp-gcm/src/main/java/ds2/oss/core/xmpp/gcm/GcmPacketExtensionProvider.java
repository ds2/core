/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.xmpp.gcm;

import ds2.oss.core.api.annotations.SmackPEProvider;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.xmlpull.v1.XmlPullParser;

/**
 * Taken from the android gcm tutorial, this extension provider registers any
 * incoming package with a specific namespace to be handled with this extension.
 * It will basically find the first text node within the received package, and
 * expects this node to be a json document. This json document is given and
 * returned as its packet extension content.
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
