/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.xmpp.gcm.sample;

import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.JsonCodec;
import ds2.oss.core.api.xmpp.IPacketIdProvider;
import ds2.oss.core.xmpp.gcm.GcmActionListener;
import ds2.oss.core.xmpp.gcm.GcmDownstreamMessage;
import ds2.oss.core.xmpp.gcm.IGcmSupport;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dstrauss
 */
@Alternative
public class AlternateGcmActionsListener implements GcmActionListener {

    private static final Logger LOG = LoggerFactory.getLogger(AlternateGcmActionsListener.class);
    @Inject
    private IGcmSupport gcm;
    @Inject
    private IPacketIdProvider numGen;

    @Override
    public void onUpstreamMessage(String json, GcmDownstreamMessage m) {
        LOG.info("upstream message: {}", json);
        LOG.info("Obj is {}", m);
        GcmDownstreamMessage rc = new GcmDownstreamMessage();
        rc.setCollapseKey("1");
        rc.setDelayWhileIdle(false);
        rc.setTtl(100000L);
        rc.setTo(m.getFrom());
        rc.setMessageId(numGen.getNextId());
        Map<String, String> data = new HashMap<>();
        data.put("msg", "Message received successfully!");
        data.put("hello", "world");
        rc.setData(data);
        gcm.sendMessage(rc);
    }

    @Override
    public void onAckMessage(String json) {
        LOG.info("ack message: {}", json);
    }

    @Override
    public void onNackMessage(String json) {
        LOG.info("nack message: {}", json);
    }

    @Override
    public void onControlMessage(String json) {
        LOG.info("control message: {}", json);
    }

}
