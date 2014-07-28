package ds2.oss.core.xmpp.gcm;

import java.net.Socket;
import org.jivesoftware.smack.packet.DefaultPacketExtension;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.util.StringUtils;

/**
 * Google Cloud Messaging packet extension. Taken from the android developer
 * website.
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
     * @param json the json content
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
