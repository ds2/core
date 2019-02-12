package ds2.oss.core.jee.rest.configuration;

import javax.ws.rs.client.Client;

@FunctionalInterface
public interface ClientConfigurer {
    void configure(Client client);
}
