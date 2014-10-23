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
