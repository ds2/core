/**
 * 
 */
package ds2.oss.core.jee.rest;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author dstrauss
 *         
 */
@Priority(Priorities.HEADER_DECORATOR)
public class HeaderResponseLogger implements ClientResponseFilter {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    /*
     * (non-Javadoc)
     * @see javax.ws.rs.client.ClientResponseFilter#filter(javax.ws.rs.client.ClientRequestContext,
     * javax.ws.rs.client.ClientResponseContext)
     */
    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        MultivaluedMap<String, String> headers = responseContext.getHeaders();
        for (Entry<String, List<String>> s : headers.entrySet()) {
            LOG.debug("Response header: {} = {}", s.getKey(), s.getValue());
        }
    }
    
}
