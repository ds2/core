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
package ds2.oss.core.jee.rest.client;

import ds2.oss.core.api.JaxRsClientException;
import ds2.oss.core.jee.rest.client.configuration.SocketErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.*;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * An abstract contract for a JaxRS client. Implementations of this contract can be
 * embedded into session scoped services. Configuration on runtime is done by using
 * specific filters, added on client initialisation.
 * <p>To use it, you need to create a JaxRS client by using the ClientBuilder.</p>
 *
 * @param <E> an exception type of transport errors.
 * @author dstrauss
 * @see SocketErrorHandler
 */
public abstract class AbstractJaxRsClient<E extends JaxRsClientException> implements JaxRsClient<E> {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    /**
     * The supported media types to send.
     */
    protected final List<MediaType> supportedMediaTypes;
    private SocketErrorHandler<E> defaultErrorHandler;
    protected boolean closeAfterParse = true;

    /**
     * Inits this object.
     */
    protected AbstractJaxRsClient() {
        supportedMediaTypes = new ArrayList<>(2);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_TYPE);
        defaultErrorHandler = e -> {
            LOG.debug("Got this exception here:", e);
            E e2 = getExceptionOnClientAccess(e);
            if (e2 != null) {
                throw e2;
            }
        };
    }

    /**
     * Performs a GET operation.
     *
     * @param wt the web target to access
     * @param eh the socket error handler
     * @return the response, or null if the socket handler does nothing
     * @throws E an exception in case the error handler throws an exception when a socket error
     *           occurred
     */
    protected Response performGET(WebTarget wt, SocketErrorHandler<E> eh) throws E {
        LOG.debug("Performing GET on {}..", wt.getUri());
        Response rc = null;
        try {
            rc = wt.request(supportedMediaTypes.toArray(new MediaType[supportedMediaTypes.size()])).get();
            afterResponse(rc);
            return rc;
        } catch (Exception e) {
            LOG.debug("We received an exception here when performing the request!", e);
            eh.handleNetworkException(e);
        } finally {
            LOG.debug("Done with GET query on {}", wt.getUri());
        }
        LOG.warn("The client has received an exception, so we will return null as a response here");
        return null;
    }

    /**
     * Performs a GET operation.
     *
     * @param wt the web target to access
     * @param eh the socket error handler
     * @return the response, or null if the socket handler does nothing
     * @throws E an exception in case the error handler throws an exception when a socket error
     *           occurred
     */
    protected Response performPUTJson(WebTarget wt, Object ent, SocketErrorHandler<E> eh) throws E {
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
            eh.handleNetworkException(e);
        } finally {
            LOG.debug("Done with GET query on {}", wt.getUri());
        }
        LOG.warn("The client has received an exception, so we will return null as a response here");
        return null;
    }

    protected Response performPUTJson(WebTarget wt, Object ent) throws E {
        return performPUTJson(wt, ent, defaultErrorHandler);
    }

    protected Response performPOSTFormUrlencoded(WebTarget wt, MultivaluedMap<String, String> map) throws E {
        return performPOSTFormUrlencoded(wt, map, defaultErrorHandler);
    }

    protected Response performPOSTFormUrlencoded(WebTarget wt, MultivaluedMap<String, String> map, SocketErrorHandler<E> eh) throws E {
        LOG.debug("Performing POST on {}..", wt.getUri());
        Response rc = null;
        try {
            Form form = new Form(map);
            Entity<Form> entity = Entity.form(form);
            rc = wt.request(supportedMediaTypes.toArray(new MediaType[supportedMediaTypes.size()])).post(entity);
            afterResponse(rc);
            return rc;
        } catch (Exception e) {
            LOG.debug("We received an exception here when performing the request!", e);
            eh.handleNetworkException(e);
        } finally {
            LOG.debug("Done with POST query on {}", wt.getUri());
        }
        LOG.warn("The client has received an exception, so we will return null as a response here");
        return null;
    }

    /**
     * Performs a GET operation on the given web target.
     *
     * @param wt the web target
     * @return a response
     * @throws E if an error occurred
     */
    protected Response performGET(WebTarget wt) throws E {
        return performGET(wt, defaultErrorHandler);
    }

    /**
     * Dummy method to print all headers of the given response.
     *
     * @param rc the response
     * @see ds2.oss.core.jee.rest.client.filters.ClientHeaderRequestLogger
     * @deprecated Better use the client filter for this.
     */
    @Deprecated
    public static void printHeaders(Response rc) {
        LOG.debug("Printing headers:");
        MultivaluedMap<String, Object> headers = rc.getHeaders();
        for (Entry<String, List<Object>> s : headers.entrySet()) {
            LOG.debug("Header: {} = {}", s.getKey(), s.getValue());
        }
        LOG.debug("Done printing headers");
    }

    /**
     * <p>This method should return an error when the client tried to perform a request to a resource
     * but failed. Possibly due to a socket timeout etc. May return null in case no exception should
     * be thrown and the client implementation should deal with a null response.
     * </p>
     * Be aware that THIS method is not to deal with HTTP 400 errors etc. This method is only for
     * socket/technical errors.
     *
     * @param e the exception received from the client
     * @return an exception instance to throw
     */
    protected abstract E getExceptionOnClientAccess(Exception e);

    /**
     * Checks the response if it contains an error code.
     *
     * @param response
     * @throws E if an error occurred
     */
    protected void parseResponseForErrors(Response response) throws E {
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
     * Closes the response object after it is no longer needed. Typically you call this method AFTER you checked for
     * the response object, if it is an error etc.
     *
     * @param response the response object
     */
    protected static void closeResponseFinally(Response response) {
        LOG.debug("Closing response object");
        if (response != null) {
            try {
                response.close();
            } catch (IllegalStateException e) {
                LOG.debug("Response may be closed already!", e);
            } catch (ProcessingException e) {
                LOG.debug("Response may be closed already!", e);
            }
        }
    }

    /**
     * Parses a response.
     *
     * @param response the response
     * @param c        the target class to map the response to
     * @return the response dto
     * @throws E if an error occurred
     */
    protected <C> C parseResponse(Response response, Class<C> c) throws E {
        if (response == null) {
            return null;
        }
        C rc = null;
        parseResponseForErrors(response);
        if (c != null) {
            rc = readResponseAs(response, c);
        }
        if (closeAfterParse) {
            closeResponseFinally(response);
        }
        return rc;
    }

    /**
     * Converts the given response into a list of objects of the response does not contain an error.
     *
     * @param response the response
     * @param c        the target entity in the list
     * @return the list of entities
     * @throws E the entity type
     * @deprecated Due to the nature of class inspection on runtime we discourage the use of this method. Please use the other one.
     */
    @Deprecated
    protected <C> List<C> parseResponseAsList(Response response, Class<C> c) throws E {
        if (response == null) {
            return null;
        }
        parseResponseForErrors(response);
        List<C> rc = response.readEntity(new GenericType<List<C>>() {
            // nothing special to do
        });
        if (closeAfterParse) {
            closeResponseFinally(response);
        }
        return rc;
    }

    /**
     * Converts the given response into a list of objects of the response does not contain an error.
     *
     * @param response the response
     * @param c        the generic type instance to use for mapping the json
     * @return the list of entities
     * @throws E the entity type
     */
    protected <C, G extends GenericType<List<C>>> List<C> parseResponseAsList(Response response, G c) throws E {
        if (response == null) {
            return null;
        } else {
            parseResponseForErrors(response);
            List<C> rc = response.readEntity(c);
            if (this.closeAfterParse) {
                closeResponseFinally(response);
            }

            return rc;
        }
    }

    /**
     * Executed after we get a response. This is before dealing with any HTTP 40x errors etc. We
     * have a response. So, we can do something with it. Perhaps logging it.
     *
     * @param r the response
     */
    protected void afterResponse(Response r) {
        // do something with it
    }

    /**
     * Actions to do to handle an error response from a resource. This is to handle some responses like
     * 400 and greater. Possibly you want to throw a specific error by decoding the response
     * and getting a possible error code from the response.
     *
     * @param response the response
     * @param thisType the received media type of the response
     * @throws E an exception
     */
    protected abstract void handleError(Response response, MediaType thisType) throws E;

    protected Response performDELETE(WebTarget wt, SocketErrorHandler<E> eh) throws E {
        LOG.debug("Performing DELETE on {}..", wt.getUri());
        Response rc = null;
        try {
            rc = wt.request(supportedMediaTypes.toArray(new MediaType[supportedMediaTypes.size()])).delete();
            afterResponse(rc);
            return rc;
        } catch (Exception e) {
            LOG.debug("We received an exception here when performing the request!", e);
            eh.handleNetworkException(e);
        } finally {
            LOG.debug("Done with GET query on {}", wt.getUri());
        }
        LOG.warn("The client has received an exception, so we will return null as a response here");
        return null;
    }

    /**
     * Internal method to handle possible checks of the response.
     *
     * @param response    the response
     * @param targetClass the possible target class to read the response into
     * @param <E>         the type of the response object
     * @return the object on success, otherwise null
     */
    protected <E> E readResponseAs(Response response, Class<E> targetClass) {
        E rc = null;
        try {
            rc = response.readEntity(targetClass);
        } catch (ProcessingException e) {
            LOG.debug("Could not read response into {}!", targetClass, e);
        }
        return rc;
    }

    /**
     * Performs a delete on the given web target.
     *
     * @param wt the web target
     * @return the response received
     * @throws E an exception in case of a transport error
     */
    protected Response performDELETE(WebTarget wt) throws E {
        return performDELETE(wt, defaultErrorHandler);
    }
}
