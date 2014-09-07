/**
 * 
 */
package ds2.oss.core.api.options;

import ds2.oss.core.api.IErrorData;

/**
 * Error on creating an option value.
 * 
 * @author dstrauss
 * @version 0.3
 *
 */
public class CreateOptionValueException extends OptionException {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = -2066873198693357462L;
    
    /**
     * Inits the exception.
     * 
     * @param d
     *            the error data
     */
    public CreateOptionValueException(IErrorData d) {
        super(d);
    }
    
    /**
     * Inits the exception.
     * 
     * @param d
     *            the error data
     * @param t
     *            the error cause
     */
    public CreateOptionValueException(IErrorData d, Throwable t) {
        super(d, t);
    }
    
}
