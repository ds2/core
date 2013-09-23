/**
 * 
 */
package ds2.oss.core.crypto;

import ds2.oss.core.api.crypto.IvEncodedContent;

/**
 * The Iv based encoded content.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class IvEncodedContentDto extends EncodedContentDto implements IvEncodedContent {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = -2257161303770878885L;
    /**
     * The init vector.
     */
    private byte[] initVector;
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.crypto.IvEncodedContent#getInitVector()
     */
    @Override
    public byte[] getInitVector() {
        return initVector;
    }
    
    /**
     * Sets the init vector.
     * 
     * @param iv
     *            the initVector to set
     */
    public void setInitVector(final byte[] iv) {
        initVector = iv;
    }
    
}
