/*
 * Copyright 2012-2015 Dirk Strauss
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
    public OptionException(final IErrorData d) {
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
    public OptionException(final IErrorData d, final Throwable t) {
        super(d, t);
    }

}
