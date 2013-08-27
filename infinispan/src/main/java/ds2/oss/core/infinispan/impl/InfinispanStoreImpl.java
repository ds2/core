package ds2.oss.core.infinispan.impl;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

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
@ApplicationScoped
public class InfinispanStoreImpl<K, V extends Persistable<K>> implements InfinispanStore<K, V> {
    /**
     * The cache instance.
     */
    @Inject
    @ds2.oss.core.api.es.InfinispanStore
    private Cache<K, V> cache;
    
    /**
     * Inits the impl.
     * 
     */
    public InfinispanStoreImpl() {
        
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
