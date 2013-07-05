package ds2.oss.core.api;

/**
 * The infinispan service.
 */
public interface InfinispanService {
    <E> E store(E e);
}
