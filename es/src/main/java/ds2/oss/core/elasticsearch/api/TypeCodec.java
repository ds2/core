/**
 * 
 */
package ds2.oss.core.elasticsearch.api;

import java.util.Map;

/**
 * A contract for a codec.
 * 
 * @author dstrauss
 * @param <T>
 *            The type to encode or decode
 * @version 0.2
 */
public interface TypeCodec<T> {
    String toJsonMap(T t);
    
    T toDto(Class<T> c, Map<String, Object> o);
    
    String getIndex();
    
    String getIndexType();
    
    String getMapping();
}
