/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package ds2.oss.core.api;

/**
 * Created by deindesign on 22.03.16.
 */
public class CoreRuntimeException extends RuntimeException {
    /**
     * The error data.
     */
    private final IErrorData errorData;

    /**
     * Inits the exception with the given error data.
     *
     * @param d
     *            the error data
     */
    public CoreRuntimeException(final IErrorData d) {
        super("" + d);
        errorData = d;
    }

    /**
     * Inits the exception with the given error data and cause.
     *
     * @param d
     *            the error data
     */
    public CoreRuntimeException(final IErrorData d, String s) {
        super(s);
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
    public CoreRuntimeException(final IErrorData d, final Throwable t) {
        super("" + d, t);
        errorData = d;
    }

    public CoreRuntimeException(final IErrorData d, final String msg, final Throwable t){
        super(msg, t);
        errorData=d;
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
