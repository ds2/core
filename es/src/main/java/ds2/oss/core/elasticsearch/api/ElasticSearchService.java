/**
 * 
 */
package ds2.oss.core.elasticsearch.api;

/**
 * Contract to access an elastic search node.
 * 
 * @author dstrauss
 * @version 0.2
 */
public interface ElasticSearchService {
    /**
     * Puts an object into the index.
     * 
     * @param t
     *            the object to put
     * @param <T>
     *            the type to put
     * @return the object
     */
    <T> T put(T t, TypeCodec<T> codec);
}
