/**
 * 
 */
package ds2.oss.core.options.api;

/**
 * Idea for a value codec contract.
 * 
 * @author dstrauss
 * @version 0.3
 * @param <V>
 *            the value type
 */
public interface ValueCodec<V> {
    String toString(V v);
    
    V toValue(String s);
}
