/**
 * 
 */
package ds2.oss.core.options.api;


/**
 * Support for encrypting or decrypting option values.
 * 
 * @author dstrauss
 * @version 0.3
 *
 */
public interface OptionValueEncrypter {
    /**
     * Encrypts a given string into a base64 string.
     * 
     * @param s
     *            the string to encrypt
     * @return the encrypted value, as base64
     */
    String encrypt(String s);
    
    /**
     * Decrypts the base64 string into the real string.
     * 
     * @param s
     *            the base64 encrypted value
     * @return the real string
     */
    String decrypt(String s);
}
