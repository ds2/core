/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
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
package ds2.oss.core.elasticsearch.impl.node.local;

import ds2.oss.core.elasticsearch.impl.AbstractNodeImpl;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.interceptor.Interceptor;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

/**
 * A local node generator.
 *
 * @author dstrauss
 * @version 0.2
 */
@ApplicationScoped
@Alternative
@Priority(Interceptor.Priority.LIBRARY_BEFORE + 15)
public class LocalNodeImpl extends AbstractNodeImpl<Client> {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(LocalNodeImpl.class);

    /**
     * Actions to perform at start.
     */
    @PostConstruct
    public void onInit() {
        try {
            LOG.debug("Starting configuration");
            final NodeBuilder nb = NodeBuilder.nodeBuilder();
            final Settings setts = ImmutableSettings.settingsBuilder().loadFromClasspath("localNode.yml").build();
            final Node n = nb.settings(setts).build();
            LOG.debug("Starting local node.");
            n.start();
            client = n.client();
            client.admin().cluster().prepareHealth().setWaitForYellowStatus().get();
            LOG.debug("Local index node is up");
        } catch (RuntimeException e) {
            LOG.error("Error when starting the local nodes..", e);
            throw e;
        }

    }

}
