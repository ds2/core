/**
 * 
 */
package ds2.oss.core.elasticsearch.impl;

import java.net.InetSocketAddress;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

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
     * Inits the node.
     */
    public TransportClientNode() {
        // nothing special to do
    }
    
    /**
     * Actions to perform on init.
     */
    @PostConstruct
    public void onInit() {
        final Settings setts =
            ImmutableSettings.settingsBuilder()
                .loadFromClasspath("/transportClientNode.yml").build();
        client =
            new TransportClient(setts)
                .addTransportAddress(new InetSocketTransportAddress(
                    "localhost", 9300));
    }
    
    @Override
    public void addTransport(final InetSocketAddress... isa) {
        needsLock = true;
        lock.lock();
        try {
            for (InetSocketAddress is : isa) {
                client.addTransportAddress(new InetSocketTransportAddress(is));
            }
            client.admin().cluster().prepareHealth().setWaitForYellowStatus()
                .execute().actionGet();
        } finally {
            lock.unlock();
            needsLock = false;
        }
    }
    
    @Override
    public void removeTransport(final InetSocketAddress... isa) {
        lock.lock();
        try {
            for (InetSocketAddress is : isa) {
                client
                    .removeTransportAddress(new InetSocketTransportAddress(is));
            }
            client.admin().cluster().prepareHealth().setWaitForYellowStatus()
                .execute().actionGet();
        } finally {
            lock.unlock();
        }
    }
    
}
