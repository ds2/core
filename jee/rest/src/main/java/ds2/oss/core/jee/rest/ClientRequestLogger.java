package ds2.oss.core.jee.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

/**
 * Created by dstrauss on 19.04.17.
 */
@Priority(Priorities.ENTITY_CODER)
public class ClientRequestLogger implements ClientRequestFilter {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private int maxEntitySize = 1024 * 8;

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        if (LOG.isDebugEnabled() && requestContext.hasEntity()) {
            LOG.debug("Entity to send: {}", requestContext.getEntity());
        }
    }

}
