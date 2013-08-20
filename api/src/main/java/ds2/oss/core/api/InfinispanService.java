package ds2.oss.core.api;

/**
 * The infinispan service.
 * 
 * @version 0.3
 * @author dstrauss
 * @param <K>
 *            the key type
 * @param <V>
 *            the value type
 */
public interface InfinispanService<K, V extends Persistable<K>> {
    /**
     * Stores an item in the cluster.
     * 
     * @param e
     *            the item to store
     * @return the item again
     */
    V store(V e);
    
    /**
     * Returns the value with the given key.
     * 
     * @param k
     *            the key
     * @return the value
     */
    V get(K k);
}
