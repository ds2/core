/**
 * 
 */
package ds2.oss.core.elasticsearch.api;

/**
 * A codec contract to deal with JSON strings via Gson.
 * 
 * @author dstrauss
 * @version 0.2
 */
public interface GsonCodec {
    /**
     * Encodes a given type into a json.
     * 
     * @param t
     *            the type
     * @return the json to use, or null if an error occurred.
     */
    <T> String encode(T t);
    
    /**
     * Decodes a json string into a type.
     * 
     * @param c
     *            the class of the type
     * @param json
     *            the json string
     * @return the created type, or null if an error occurred
     */
    <T> T decode(Class<T> c, String json);
}
