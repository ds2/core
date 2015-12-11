/**
 * 
 */
package ds2.oss.core.api;

/**
 * @author dstrauss
 *         
 */
public class JaxRsClientException extends CoreException {
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 5656212736003194700L;
    
    /**
     * Inits this object.
     * 
     * @param d
     */
    public JaxRsClientException(IErrorData d) {
        super(d);
    }
    
    public JaxRsClientException(IErrorData d, Throwable t) {
        super(d, t);
    }
    
}
