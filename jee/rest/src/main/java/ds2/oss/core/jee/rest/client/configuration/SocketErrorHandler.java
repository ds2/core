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
package ds2.oss.core.jee.rest.client.configuration;

import ds2.oss.core.api.JaxRsClientException;

/**
 * This contract deals with errors on application level of a jax rs call.
 *
 * @author dstrauss
 */
public interface SocketErrorHandler<E extends JaxRsClientException> {
    /**
     * Handles the network exception that occurred during jaxrs client call.
     *
     * @param e the network exception
     * @throws E the business exception
     */
    void handleNetworkException(Exception e) throws E;
}
