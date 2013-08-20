package ds2.oss.core.api;

import ds2.oss.core.api.es.Cacheable;

/**
 * The infinispan service.
 * 
 * @version 0.3
 * @author dstrauss
 */
public interface InfinispanService<K,V extends Cacheable> {
    /**
     * Stores an item in the cluster.
     * 
     * @param e
     *            the item to store
     * @return the item again
     */
    V store(V e);
  V get(String k);
}
