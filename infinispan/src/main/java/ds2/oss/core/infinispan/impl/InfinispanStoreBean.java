package ds2.oss.core.infinispan.impl;

import java.lang.invoke.MethodHandles;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.infinispan.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.InfinispanStore;
import ds2.oss.core.api.Persistable;

/**
 * The impl.
 * 
 * @author dstrauss
 * @version 0.3
 * 
 * @param <K>
 *            the key type
 * @param <V>
 *            the value type
 */
public class InfinispanStoreBean<K, V extends Persistable<K>> implements InfinispanStore<K, V> {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    /**
     * The cache instance.
     */
    private Cache<K, V> cache;
    
    @PostConstruct
    public void onLoad() {
        LOG.debug("I am alive");
    }
    
    /**
     * Actions to perform at stop.
     */
    @PreDestroy
    public void onStop() {
        LOG.debug("Stopping cache..");
        cache.stop();
    }
    
    @Override
    public V store(final V e) {
        if (e == null) {
            throw new IllegalArgumentException("You must give a value to store!");
        }
        if (e.getId() == null) {
            throw new IllegalArgumentException("Given persistable has no id!");
        }
        return cache.put(e.getId(), e);
    }
    
    @Override
    public V get(final K k) {
        return cache.get(k);
    }
    
    void setCache(Cache<K, V> foundCache) {
        cache = foundCache;
    }
}
