/**
 * 
 */
package ds2.oss.core.api.crypto;

/**
 * Contract for a decrypter. It is up to the implementations how to deal with the given encoded
 * content. Say, what hash algorithm to use etc.
 * 
 * @author dstrauss
 * @version 0.3
 * @param <E>
 *            the type of the encoded content
 *
 */
public interface Decrypter<E extends EncodedContent> {
    /**
     * Decrypts the content.
     * 
     * @param encodedContent
     *            the encoded content
     * @return the bytes
     */
    byte[] decode(E encodedContent);
}
