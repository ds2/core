/**
 * 
 */
package ds2.oss.core.elasticsearch.impl;

import java.net.InetSocketAddress;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.PreDestroy;

import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.elasticsearch.api.ElasticSearchNode;

/**
 * An abstract node implementation.
 * 
 * @author dstrauss
 * @version 0.2
 * @param <T>
 *            The type of the node
 */
public abstract class AbstractNodeImpl<T extends Client>
    implements
    ElasticSearchNode {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory
        .getLogger(AbstractNodeImpl.class);
    /**
     * A lock.
     */
    protected final Lock lock = new ReentrantLock();
    /**
     * Flag to indicate that a lock is required on the client.
     */
    protected boolean needsLock;
    /**
     * The node instance.
     */
    protected T client;
    
    /**
     * Sets up the node impl.
     */
    public AbstractNodeImpl() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Actions to perform on shutdown.
     */
    @PreDestroy
    public void onShutdown() {
        client.close();
    }
    
    @Override
    public Client get() {
        if (!needsLock) {
            return client;
        }
        lock.lock();
        try {
            return client;
        } finally {
            lock.unlock();
        }
    }
    
    @Override
    public void addTransport(final InetSocketAddress... isa) {
        LOG.info("Ignoring");
    }
    
    @Override
    public void removeTransport(final InetSocketAddress... isa) {
        LOG.info("Ignoring");
    }
}
