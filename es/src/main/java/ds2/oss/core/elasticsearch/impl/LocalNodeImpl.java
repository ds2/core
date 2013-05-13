/**
 * 
 */
package ds2.oss.core.elasticsearch.impl;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A local node generator.
 * 
 * @author dstrauss
 * @version 0.2
 */
@ApplicationScoped
@Alternative
public class LocalNodeImpl extends AbstractNodeImpl<Client> {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory
        .getLogger(LocalNodeImpl.class);
    
    /**
     * Nothing to do.
     */
    public LocalNodeImpl() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Actions to perform at start.
     */
    @PostConstruct
    public void onInit() {
        LOG.debug("Starting configuration");
        final NodeBuilder nb = NodeBuilder.nodeBuilder();
        final Settings setts =
            ImmutableSettings.settingsBuilder()
                .loadFromClasspath("localNode.yml").build();
        final Node n = nb.settings(setts).build();
        LOG.debug("Starting local node.");
        n.start();
        client = n.client();
        client.admin().cluster().prepareHealth().setWaitForYellowStatus()
            .execute().actionGet();
        LOG.debug("Local index node is up");
    }
    
}
