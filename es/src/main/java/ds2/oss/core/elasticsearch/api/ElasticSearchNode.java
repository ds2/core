/**
 * 
 */
package ds2.oss.core.elasticsearch.api;

import java.net.InetSocketAddress;

import org.elasticsearch.client.Client;

/**
 * A contract for an ES node.
 * 
 * @author dstrauss
 * @version 0.2
 */
public interface ElasticSearchNode {
    /**
     * Returns a configured client.
     * 
     * @return a client
     */
    Client get();
    
    /**
     * Adds a list of new transport endpoints.
     * 
     * @param isa
     *            the new endpoints to add
     */
    void addTransport(InetSocketAddress... isa);
    
    /**
     * Removes a list of transports.
     * 
     * @param isa
     *            the transports to remove.
     */
    void removeTransport(InetSocketAddress... isa);
}
