/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.xmpp.gcm.sample;

import ds2.oss.core.xmpp.gcm.GcmActionListener;
import javax.enterprise.inject.Alternative;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dstrauss
 */
@Alternative
public class AlternateGcmActionsListener implements GcmActionListener {

    private static final Logger LOG = LoggerFactory.getLogger(AlternateGcmActionsListener.class);

    @Override
    public void onUpstreamMessage(String json) {
        LOG.info("upstream message: {}", json);
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
