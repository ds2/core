/**
 * 
 */
package ds2.oss.core.crypto;

import ds2.oss.core.api.crypto.EncodedContent;

/**
 * A simple dto.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class EncodedContentDto implements EncodedContent {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 8010600132495793892L;
    /**
     * The encoded bytes.
     */
    private byte[] encoded;
    
    @Override
    public byte[] getEncoded() {
        return encoded;
    }
    
    /**
     * Sets the encoded bytes.
     * 
     * @param enc
     *            the encoded to set
     */
    public void setEncoded(final byte[] enc) {
        this.encoded = enc;
    }
    
}
