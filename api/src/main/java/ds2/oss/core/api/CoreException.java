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
