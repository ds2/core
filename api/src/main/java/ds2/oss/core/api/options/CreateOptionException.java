/**
 * 
 */
package ds2.oss.core.api.options;

import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.IErrorData;

/**
 * Exception to address problems when creating an option.
 * 
 * @author dstrauss
 * @version 0.3
 *
 */
public class CreateOptionException extends CoreException {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = -5582286792957737739L;
    
    /**
     * Inits the exception with the given error data.
     * 
     * @param d
     *            the error data
     */
    public CreateOptionException(IErrorData d) {
        super(d);
    }
    
    /**
     * Inits the exception with the given error data.
     * 
     * @param d
     *            the error data
     * @param t
     *            the causing exception
     */
    public CreateOptionException(IErrorData d, Throwable t) {
        super(d, t);
    }
    
}
