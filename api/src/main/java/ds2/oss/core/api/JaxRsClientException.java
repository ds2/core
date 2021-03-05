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
 * This type of exception is thrown on transport level. Say, it is usually
 * thrown when a network error occurs.
 *
 * @author dstrauss
 */
public class JaxRsClientException extends CoreException {
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 5656212736003194700L;

    /**
     * Inits this object.
     *
     * @param d
     */
    public JaxRsClientException(IErrorData d) {
        super(d);
    }

    public JaxRsClientException(IErrorData d, String msg) {
        super(d, msg);
    }

    public JaxRsClientException(IErrorData d, String msg, Throwable t) {
        super(d, msg, t);
    }

    public JaxRsClientException(IErrorData d, Throwable t) {
        super(d, t);
    }

}
