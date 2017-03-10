package ds2.oss.core.jee.rest.tests;

import ds2.oss.core.jee.rest.AbstractJaxRsClient;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.SocketTimeoutException;

/**
 * Created by dstrauss on 21.02.17.
 */
@SessionScoped
public class MySessionScopedService extends AbstractJaxRsClient<MyJaxException> {
    @Inject
    private Client client;

    @Override
    protected MyJaxException getExceptionOnClientAccess(Exception e) {
        if (e instanceof SocketTimeoutException) {
            return new MyJaxException(1);
        }
        return new MyJaxException(2, e);
    }

    @Override
    protected void handleError(Response response, MediaType thisType) throws MyJaxException {
        throw new MyJaxException(3);
    }
}
