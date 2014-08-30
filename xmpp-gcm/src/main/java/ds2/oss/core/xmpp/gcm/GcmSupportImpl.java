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

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.JsonCodec;
import ds2.oss.core.api.xmpp.IXmppSupport;

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
