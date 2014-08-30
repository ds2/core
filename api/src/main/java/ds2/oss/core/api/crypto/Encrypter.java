/**
 * 
 */
package ds2.oss.core.api.crypto;

/**
 * Contract for an encrypter. It is up to the implementation how to deal with the given sequence of
 * bytes to encode. Whether to use a specific hash algorithm etc.
 * 
 * @author dstrauss
 * @version 0.3
 * @param <E>
 *            the type of the encoded content
 */
public interface Encrypter<E extends EncodedContent> {
    /**
     * Encodes a given sequence of bytes.
     * 
     * @param b
     *            the byte sequence
     * @return the encoded content.
     */
    E encode(byte[] b);
}
