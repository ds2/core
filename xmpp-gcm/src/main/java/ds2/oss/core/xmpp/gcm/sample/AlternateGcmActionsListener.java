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
package ds2.oss.core.xmpp.gcm.sample;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.xmpp.IPacketIdProvider;
import ds2.oss.core.xmpp.gcm.GcmActionListener;
import ds2.oss.core.xmpp.gcm.GcmDownstreamMessage;
import ds2.oss.core.xmpp.gcm.IGcmSupport;

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
