/**
 * 
 */
package ds2.oss.core.elasticsearch.api;

/**
 * The codec provider.
 * 
 * @author dstrauss
 * @version 0.2
 */
public interface CodecProvider {
    /**
     * Finds a codec for the given class type.
     * 
     * @param c
     *            the class type
     * @param <T>
     *            the type
     * @return the codec, or null if not found
     */
    <T> TypeCodec<T> findFor(Class<T> c);
}
