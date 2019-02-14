/*
 * Copyright 2019 DS/2 <dstrauss@ds-2.de>
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
package ds2.oss.core.jee.rest;

import ds2.oss.core.api.JaxRsClientException;
import ds2.oss.core.jee.rest.client.JaxRsClientConfiguration;
import ds2.oss.core.jee.rest.client.configuration.ClientBuilderGenerator;
import ds2.oss.core.jee.rest.client.configuration.ClientConfigurer;
import ds2.oss.core.jee.rest.client.configuration.ClientGenerator;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public interface JaxRsHelper {
    /**
     * Generates a jaxrs client.
     *
     * @param clientBuilder    the client builder, or null
     * @param clientGenerator  the client generator, or null
     * @param clientConfigurer the client configurer, or null
     * @param configuration    the runtime configuration, or null
     * @return the jax rs client to use
     * @throws JaxRsClientException if an error occurred
     */
    static Client generateClient(ClientBuilderGenerator clientBuilder, ClientGenerator clientGenerator, ClientConfigurer clientConfigurer, JaxRsClientConfiguration configuration) throws JaxRsClientException {
        ClientBuilder clientBuilder1;
        if (clientBuilder != null) {
            clientBuilder1 = clientBuilder.generateClientBuilder(configuration);
        } else {
            clientBuilder1 = ClientBuilder.newBuilder();
        }
        Client client;
        if (clientGenerator != null) {
            client = clientGenerator.generate(clientBuilder1, configuration);
        } else {
            client = clientBuilder1.build();
        }
        if (clientConfigurer != null) {
            clientConfigurer.configure(client);
        }
        return client;
    }
}
