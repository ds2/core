package ds2.oss.core.api.crypto;

import java.io.Serializable;

/**
 * A DTO contract for an object having an encoded content.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface EncodedContent extends Serializable {
    /**
     * Returns the encoded bytes of the content.
     * 
     * @return the encoded content, or null if not set
     */
    byte[] getEncoded();
}
