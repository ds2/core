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
     * @param index
     *            The index name to put the object into
     * 
     * @param t
     *            the object to put
     * @param codec
     *            the codec to use
     * @param <T>
     *            the type to put
     * @return the object
     */
    <T> T put(String index, T t, TypeCodec<T> codec);
}
