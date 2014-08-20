package ds2.oss.core.infinispan.impl;

import javax.annotation.PreDestroy;

import org.infinispan.Cache;

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
public class InfinispanStoreImpl<K, V extends Persistable<K>> implements InfinispanStore<K, V> {
    /**
     * The cache instance.
     */
    private Cache<K, V> cache;
    
    /**
     * Actions to perform at stop.
     */
    @PreDestroy
    public void onStop() {
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
    
    public void setCache(Cache<K, V> foundCache) {
        cache = foundCache;
    }
}
