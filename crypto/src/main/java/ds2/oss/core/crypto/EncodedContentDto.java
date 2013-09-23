/**
 * 
 */
package ds2.oss.core.crypto;

import java.util.Arrays;

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
        encoded = enc;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final int maxLen = 256;
        final StringBuilder builder = new StringBuilder();
        builder.append("EncodedContentDto (encoded=");
        builder.append(encoded != null ? Arrays.toString(Arrays.copyOf(encoded, Math.min(encoded.length, maxLen)))
            : null);
        builder.append(")");
        return builder.toString();
    }
    
}
