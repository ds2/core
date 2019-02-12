package ds2.oss.core.jee.rest.configuration;

import ds2.oss.core.api.JaxRsClientException;
import ds2.oss.core.jee.rest.JaxRsClientConfiguration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@FunctionalInterface
public interface ClientGenerator {
    Client generate(ClientBuilder clientBuilder, JaxRsClientConfiguration config) throws JaxRsClientException;
}
