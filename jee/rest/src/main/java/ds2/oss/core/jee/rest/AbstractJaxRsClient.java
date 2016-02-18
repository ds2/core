/**
 * 
 */
package ds2.oss.core.jee.rest;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.JaxRsClientException;

/**
 * @author dstrauss
 * @param <E>
 *            an exception type
 *            
 */
public abstract class AbstractJaxRsClient<E extends JaxRsClientException> {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    /**
     * The supported media types to send.
     */
    protected final List<MediaType> supportedMediaTypes;
    private SocketErrorHandler<E> defaultErrorHandler;
    
    /**
     * Inits this object.
     */
    protected AbstractJaxRsClient() {
        supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_TYPE);
        defaultErrorHandler = new SocketErrorHandler<E>() {
            
            @Override
            public void handleException(Exception e) throws E {
                E e2 = getExceptionOnClientAccess(e);
                if (e2 != null) {
                    throw e2;
                }
            }
        };
    }
    
    /**
     * Performs a GET operation.
     * 
     * @param wt
     *            the web target to access
     * @param eh
     *            the socket error handler
     * @return the response, or null if the socket handler does nothing
     * @throws E
     *             an exception in case the error handler throws an exception when a socket error
     *             occurred
     */
    public Response performGET(WebTarget wt, SocketErrorHandler<E> eh) throws E {
        LOG.debug("Performing GET on {}..", wt.getUri());
        try {
            Response rc = wt.request(supportedMediaTypes.toArray(new MediaType[supportedMediaTypes.size()])).get();
            afterResponse(rc);
            return rc;
        } catch (Exception e) {
            LOG.debug("We received an exception here when performing the request!", e);
            eh.handleException(e);
        } finally {
            LOG.debug("Done with GET query on {}", wt.getUri());
        }
        LOG.warn("The client has received an exception, so we will return null as a response here");
        return null;
    }
    
    /**
     * Performs a GET operation.
     * 
     * @param wt
     *            the web target to access
     * @param eh
     *            the socket error handler
     * @return the response, or null if the socket handler does nothing
     * @throws E
     *             an exception in case the error handler throws an exception when a socket error
     *             occurred
     */
    public Response performPUTJson(WebTarget wt, Object ent, SocketErrorHandler<E> eh) throws E {
        LOG.debug("Performing PUT on {}..", wt.getUri());
        try {
            Builder rc2 =
                wt.queryParam("a", "b").request(supportedMediaTypes.toArray(new MediaType[supportedMediaTypes.size()]));
            Entity<Object> entity = Entity.json(ent);
            if (ent == null) {
                entity = Entity.json("");
                // rc2.header("Content-Length", 0);
            }
            Response rc = rc2.put(entity);
            afterResponse(rc);
            return rc;
        } catch (Exception e) {
            LOG.debug("We received an exception here when performing the request!", e);
            eh.handleException(e);
        } finally {
            LOG.debug("Done with GET query on {}", wt.getUri());
        }
        LOG.warn("The client has received an exception, so we will return null as a response here");
        return null;
    }
    
    public Response performPUTJson(WebTarget wt, Object ent) throws E {
        return performPUTJson(wt, ent, defaultErrorHandler);
    }
    
    /**
     * Performs a GET operation on the given web target.
     * 
     * @param wt
     *            the web target
     * @return a response
     * @throws E
     *             if an error occurred
     */
    public Response performGET(WebTarget wt) throws E {
        return performGET(wt, defaultErrorHandler);
    }
    
    /**
     * Dummy method to print all headers of the given response.
     * 
     * @param rc
     *            the response
     */
    public static void printHeaders(Response rc) {
        LOG.debug("Printing headers:");
        MultivaluedMap<String, Object> headers = rc.getHeaders();
        for (Entry<String, List<Object>> s : headers.entrySet()) {
            LOG.debug("Header: {} = {}", s.getKey(), s.getValue());
        }
        LOG.debug("Done printing headers");
    }
    
    /**
     * This method should return an error when the client tried to perform a request to a resource
     * but failed. Possibly due to a socket timeout etc. May return null in case no exception should
     * be thrown and the client implementation should deal with a null response.
     * </P>
     * Be aware that THIS method is not to deal with HTTP 400 errors etc. This method is only for
     * socket errors.
     * 
     * @param e
     *            the exception received from the client
     * @return an exception instance to throw
     */
    protected abstract E getExceptionOnClientAccess(Exception e);
    
    /**
     * Checks the response if it contains an error code.
     * 
     * @param response
     * @throws JaxRsClientException
     */
    public void parseResponseForErrors(Response response) throws E {
        if (response == null) {
            LOG.debug("No response given to parse!");
            return;
        }
        if (response.getStatus() >= 400) {
            LOG.warn("Error occurred when performing the request: {}", response.getStatusInfo());
            // Expect error data
            MediaType thisType = response.getMediaType();
            handleError(response, thisType);
        } else {
            LOG.debug("Response code is {}", response.getStatus());
        }
    }
    
    /**
     * Closes the response object after it is no longer needed.
     * 
     * @param response
     *            the response object
     */
    public static void closeResponseFinally(Response response) {
        LOG.debug("Closing response object");
        if (response != null) {
            response.close();
        }
    }
    
    /**
     * Parses a response.
     * 
     * @param response
     *            the response
     * @param c
     *            the target class to map the response to
     * @return the response dto
     * @throws E
     *             if an error occurred
     */
    public <C> C parseResponse(Response response, Class<C> c) throws E {
        if (response == null) {
            return null;
        }
        C rc = null;
        parseResponseForErrors(response);
        if (c != null) {
            rc = response.readEntity(c);
        }
        return rc;
    }
    
    /**
     * Converts the given response into a list of objects of the response does not contain an error.
     * 
     * @param response
     *            the response
     * @param c
     *            the target entity in the list
     * @return the list of entities
     * @throws E
     *             the entity type
     */
    public <C> List<C> parseResponseAsList(Response response, Class<C> c) throws E {
        if (response == null) {
            return null;
        }
        parseResponseForErrors(response);
        List<C> rc = response.readEntity(new GenericType<List<C>>() {
            // nothing special to do
        });
        return rc;
    }
    
    /**
     * Executed after we get a response. This is before dealing with any HTTP 40x errors etc. We
     * have a response. So, we can do something with it. Perhaps logging it.
     * 
     * @param r
     *            the response
     */
    protected void afterResponse(Response r) {
        // do something with it
    }
    
    /**
     * Actions to do to handle an error response from a resource.
     * 
     * @param response
     *            the response
     * @param thisType
     *            the received media type of the response
     * @throws E
     *             an exception
     */
    protected abstract void handleError(Response response, MediaType thisType) throws E;
    
    public Response performDELETE(WebTarget wt, SocketErrorHandler<E> eh) throws E {
        LOG.debug("Performing DELETE on {}..", wt.getUri());
        try {
            Response rc = wt.request(supportedMediaTypes.toArray(new MediaType[supportedMediaTypes.size()])).delete();
            afterResponse(rc);
            return rc;
        } catch (Exception e) {
            LOG.debug("We received an exception here when performing the request!", e);
            eh.handleException(e);
        } finally {
            LOG.debug("Done with GET query on {}", wt.getUri());
        }
        LOG.warn("The client has received an exception, so we will return null as a response here");
        return null;
    }
    
    public Response performDELETE(WebTarget wt) throws E {
        return performDELETE(wt, defaultErrorHandler);
    }
}
