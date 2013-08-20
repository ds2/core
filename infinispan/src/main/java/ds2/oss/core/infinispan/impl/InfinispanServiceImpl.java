package ds2.oss.core.infinispan.impl;

import javax.annotation.PreDestroy;

import org.infinispan.Cache;

import ds2.oss.core.api.InfinispanService;
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
public class InfinispanServiceImpl<K, V extends Persistable<K>> implements InfinispanService<K, V> {
    /**
     * The cache instance.
     */
    private Cache<K, V> cache;
    
    /**
     * Inits the impl.
     * 
     * @param c
     *            the cache to use.
     */
    public InfinispanServiceImpl(final Cache<K, V> c) {
        cache = c;
    }
    
    /**
     * Actions to perform at stop.
     */
    @PreDestroy
    public void onStop() {
        cache.stop();
    }
    
    @Override
    public V store(final V e) {
        return cache.put(e.getId(), e);
    }
    
    @Override
    public V get(final K k) {
        return cache.get(k);
    }
}
