package ds2.oss.core.infinispan.api;

/**
 * The infinispan service.
 */
public interface InfinispanService {
    <E> E store(E e);
}
