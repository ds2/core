package ds2.oss.core.jee.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.lang.invoke.MethodHandles;

/**
 * Abstract contract for catching specific exceptions.
 *
 * @see javax.ws.rs.ext.Provider
 */
public abstract class AbstractRestExceptionMapper<E extends Exception> implements ExceptionMapper<E> {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public Response toResponse(E exception) {
        LOG.debug("Got this error here: {}", exception);
        Response rc = transform(exception);
        LOG.debug("Returning this response to the client: {}", rc);
        return rc;
    }

    protected abstract Response transform(E exception);
}
