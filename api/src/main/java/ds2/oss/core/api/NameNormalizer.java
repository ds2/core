/**
 * 
 */
package ds2.oss.core.api;

/**
 * Contract for a web normalizer converter. This contract is used to convert a given string into a
 * web name component to allow the given string to be used as an identifier for REST access, or
 * similar.
 * 
 * @author dstrauss
 *         
 */
public interface NameNormalizer {
    /**
     * Normalizes the given string by removing specific characters, and/or replacing them with other
     * chars.
     * 
     * @param s
     *            the string to normalize
     * @return the result
     */
    String normalize(String s);
}
