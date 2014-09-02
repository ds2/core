/**
 * 
 */
package ds2.oss.core.base.impl.db;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

import ds2.oss.core.api.crypto.IvEncodedContent;

/**
 * A db module for iv encoded content.
 * 
 * @author dstrauss
 * @version 0.3
 */
@Embeddable
public class IvEncodedContentModule implements IvEncodedContent {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 4906950450524305880L;
    /**
     * The init vector.
     */
    @Column(name = "enc_iv")
    @Lob
    private byte[] initVector;
    /**
     * The encoded data.
     */
    @Column(name = "enc_data", columnDefinition = "BLOB")
    @Lob
    private byte[] encoded;
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.crypto.EncodedContent#getEncoded()
     */
    @Override
    public byte[] getEncoded() {
        return encoded;
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.crypto.IvEncodedContent#getInitVector()
     */
    @Override
    public byte[] getInitVector() {
        return initVector;
    }
    
}
