package ds2.oss.core.api;

/**
 * The infinispan service.
 * 
 * @version 0.3
 * @author dstrauss
 */
public interface InfinispanService {
    /**
     * Stores an item in the cluster.
     * 
     * @param e
     *            the item to store
     * @param <E>
     *            the type of the item
     * @return the item again
     */
    <E> E store(E e);
}
