/*
 * Copyright 2012-2014 Dirk Strauss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ds2.oss.core.api;

/**
 * Abstract definition for any checked core exceptions.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class CoreException extends Exception {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = -1531073962392031816L;
    /**
     * The error data.
     */
    private final IErrorData errorData;
    
    /**
     * Inits a simple exception.
     * 
     * @param msg
     *            the error message
     * @deprecated Please use another constructor. This one here is for backward compatibility.
     */
    @Deprecated
    public CoreException(final String msg) {
        super(msg);
        errorData = null;
    }
    
    /**
     * Inits the exception with the given error data.
     * 
     * @param d
     *            the error data
     */
    public CoreException(final IErrorData d) {
        super();
        errorData = d;
    }
    
    /**
     * Inits the exception with the given error data and cause.
     * 
     * @param d
     *            the error data
     * @param t
     *            the cause
     */
    public CoreException(final IErrorData d, final Throwable t) {
        super(t);
        errorData = d;
    }
    
    /**
     * Returns the error data.
     * 
     * @return the error data
     */
    public IErrorData getErrorData() {
        return errorData;
    }
}
