/**
 * 
 */
package ds2.oss.core.api.crypto;

/**
 * A dto contract for an encoded content that is based on an IV.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface IvEncodedContent extends EncodedContent {
    /**
     * Returns the init vector that was used to encode the content.
     * 
     * @return the init vector
     */
    byte[] getInitVector();
}
