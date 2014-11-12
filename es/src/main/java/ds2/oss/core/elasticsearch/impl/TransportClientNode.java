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
package ds2.oss.core.elasticsearch.impl;

import java.net.InetSocketAddress;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import ds2.oss.core.elasticsearch.api.EsConfig;

/**
 * A transport client impl.
 * 
 * @author dstrauss
 * @version 0.2
 */
@ApplicationScoped
@Alternative
public class TransportClientNode extends AbstractNodeImpl<TransportClient> {
    /**
     * The ES config.
     */
    @Inject
    private EsConfig config;
    
    @Override
    public void addTransport(final InetSocketAddress... isa) {
        needsLock = true;
        lock.lock();
        try {
            for (InetSocketAddress is : isa) {
                client.addTransportAddress(new InetSocketTransportAddress(is));
            }
            client.admin().cluster().prepareHealth().setWaitForYellowStatus().execute().actionGet();
        } finally {
            lock.unlock();
            needsLock = false;
        }
    }
    
    /**
     * Actions to perform on init.
     */
    @PostConstruct
    public void onInit() {
        final ImmutableSettings.Builder sb =
            ImmutableSettings.settingsBuilder().loadFromClasspath("/transportClientNode.yml");
        sb.put("cluster.name", config.getClusterName());
        sb.put("client", true);
        if (config.getProperties() != null) {
            sb.put(config.getProperties());
        }
        final Settings setts = sb.build();
        client = new TransportClient(setts).addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
    }
    
    @Override
    public void removeTransport(final InetSocketAddress... isa) {
        lock.lock();
        try {
            for (InetSocketAddress is : isa) {
                client.removeTransportAddress(new InetSocketTransportAddress(is));
            }
            client.admin().cluster().prepareHealth().setWaitForYellowStatus().execute().actionGet();
        } finally {
            lock.unlock();
        }
    }
    
}
