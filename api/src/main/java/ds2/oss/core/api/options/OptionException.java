package ds2.oss.core.api.options;

import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.IErrorData;

/**
 * Any option related error.
 * 
 * @author dstrauss
 * @version 0.3
 *
 */
public class OptionException extends CoreException {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 4316958170744177786L;
    
    /**
     * Inits the exception.
     * 
     * @param d
     *            the error data
     */
    public OptionException(IErrorData d) {
        super(d);
    }
    
    /**
     * Inits the exception.
     * 
     * @param d
     *            the error data
     * @param t
     *            the causing error
     */
    public OptionException(IErrorData d, Throwable t) {
        super(d, t);
    }
    
}
