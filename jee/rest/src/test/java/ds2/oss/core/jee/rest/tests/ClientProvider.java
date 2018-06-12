package ds2.oss.core.jee.rest.tests;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * Created by dstrauss on 21.02.17.
 */
@Dependent
public class ClientProvider {
    @Produces
    public Client produceClient() {
        //typically, you can also add some SslContext to it
        return ClientBuilder.newClient();
    }
}
