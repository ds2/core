/**
 * 
 */
package ds2.oss.core.options.api;

/**
 * Support for encrypting or decrypting option values. Use the annotation
 * {@link ds2.oss.core.api.options.ForValueType ForValueType} to address the specific
 * implementation.
 * 
 * @author dstrauss
 * @version 0.3
 * @param <V>
 *            the value type
 *
 */
public interface OptionValueEncrypter<V> {
    /**
     * Encrypts a given string into a base64 string.
     * 
     * @param s
     *            the string to encrypt
     * @return the encrypted value, as base64
     */
    String encrypt(V s);
    
    /**
     * Decrypts the base64 string into the real string.
     * 
     * @param s
     *            the base64 encrypted value
     * @return the real string
     */
    V decrypt(String s);
}
