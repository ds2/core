/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.api;

/**
 * Abstract definition for any checked core exceptions.
 *
 * @author dstrauss
 */
public class CoreException extends Exception {

    private IErrorData errorData;

    /**
     * Inits a simple exception.
     *
     * @param msg the error message
     * @deprecated Please use another constructor. This one here is for backward
     * compatibility.
     */
    @Deprecated
    public CoreException(String msg) {
        super(msg);
    }

    public CoreException(IErrorData d) {
        super();
        errorData = d;
    }

    public CoreException(IErrorData d, Throwable t) {
        super(t);
        errorData = d;
    }

    public IErrorData getErrorData() {
        return errorData;
    }
}
