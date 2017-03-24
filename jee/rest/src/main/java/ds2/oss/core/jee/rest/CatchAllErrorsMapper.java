package ds2.oss.core.jee.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.lang.invoke.MethodHandles;

/**
 * Created by dstrauss on 24.03.17.
 */
@Provider
public class CatchAllErrorsMapper<E extends Exception> implements ExceptionMapper<E> {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Inject
    private ExceptionTransformer<E> transformer;

    @Override
    public Response toResponse(E exception) {
        LOG.debug("Got this error here: {}", exception);
        Response rc = transformer.transform(exception);
        LOG.debug("Returning this response to the client: {}", rc);
        return rc;
    }
}
