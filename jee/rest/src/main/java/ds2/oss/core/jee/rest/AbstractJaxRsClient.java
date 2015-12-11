/**
 * 
 */
package ds2.oss.core.jee.rest;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

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
    
    /**
     * Inits this object.
     */
    protected AbstractJaxRsClient() {
        supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_TYPE);
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
        LOG.debug("Performing GET on {}..", wt.getUri());
        try {
            Response rc = wt.request(supportedMediaTypes.toArray(new MediaType[supportedMediaTypes.size()])).get();
            printHeaders(rc);
            return rc;
        } catch (Exception e) {
            LOG.debug("We received an exception here when performing the request!", e);
            E e2 = getExceptionOnClientAccess(e);
            if (e2 != null) {
                throw e2;
            }
        } finally {
            LOG.debug("Done with GET query on {}", wt.getUri());
        }
        LOG.warn("The client has received an exception, so we will return null as a response here");
        return null;
    }
    
    /**
     * @param rc
     */
    private void printHeaders(Response rc) {
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
     * 
     * @param e
     *            the exception received from the client
     * @return an exception instance to throw
     */
    protected abstract E getExceptionOnClientAccess(Exception e);
    
    /**
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
    public void closeResponseFinally(Response response) {
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
    
    public <C> List<C> parseResponseAsList(Response response, Class<C> c) throws E {
        if (response == null) {
            return null;
        }
        parseResponseForErrors(response);
        List<C> rc = response.readEntity(new GenericType<List<C>>() {
        });
        return rc;
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
}
