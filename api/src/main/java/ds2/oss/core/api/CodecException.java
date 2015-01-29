/**
 * 
 */
package ds2.oss.core.api;

/**
 * @author dstrauss
 *
 */
public class CodecException extends CoreException {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = -6082960579777761486L;
    
    /**
     * @param d
     */
    public CodecException(IErrorData d) {
        super(d);
        // TODO Auto-generated constructor stub
    }
    
    /**
     * @param d
     * @param t
     */
    public CodecException(IErrorData d, Throwable t) {
        super(d, t);
        // TODO Auto-generated constructor stub
    }
    
}
