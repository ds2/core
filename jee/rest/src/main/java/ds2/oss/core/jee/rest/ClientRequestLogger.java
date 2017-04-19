package ds2.oss.core.jee.rest;

import ds2.oss.core.webtools.io.LoggingOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.invoke.MethodHandles;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by dstrauss on 19.04.17.
 */
@Priority(Priorities.ENTITY_CODER)
public class ClientRequestLogger implements ClientRequestFilter, WriterInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private int maxEntitySize = 1024 * 4;
    private final static String PROPKEY = "ds2.oss.core.rest.output.logger";

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        if (LOG.isDebugEnabled() && requestContext.hasEntity()) {
            LOG.debug("Entity to send: {}", requestContext.getEntity());
        }
        LoggingOutputStream<OutputStream> los = new LoggingOutputStream<>(requestContext.getEntityStream());
        los.setMaxLength(maxEntitySize);
        requestContext.setEntityStream(los);
        requestContext.setProperty(PROPKEY, los);
    }

    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
        Object los = context.getProperty(PROPKEY);
        context.proceed();
        if (los != null && los instanceof LoggingOutputStream) {
            LoggingOutputStream<OutputStream> thisOs = (LoggingOutputStream<OutputStream>) los;
            Charset cs = StandardCharsets.UTF_8;
            LOG.debug("Client request payload: {}", thisOs.getBytesAsString(cs));
        }
    }
}
