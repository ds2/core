/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.xmpp.gcm;

import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.JsonCodec;
import ds2.oss.core.api.xmpp.IXmppSupport;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dstrauss
 */
@ApplicationScoped
public class GcmSupportImpl implements IGcmSupport {

    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(GcmSupportImpl.class);
    @Inject
    private IXmppSupport xmpp;
    @Inject
    private JsonCodec codec;

    @Override
    public <E extends BaseJsonContent> void sendMessage(E m) {
        String json = "";
        try {
            json = codec.encode(m);
            GcmPacketExtension p = new GcmPacketExtension(json);
            xmpp.sendPacket(p.toPacket());
        } catch (CoreException ex) {
            LOG.error("Error when converting the message to json!", ex);
        }

    }
}
