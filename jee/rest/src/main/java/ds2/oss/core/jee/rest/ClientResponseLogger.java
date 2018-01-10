package ds2.oss.core.jee.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by dstrauss on 19.04.17.
 * <a href="https://github.com/jersey/jersey/blob/2.22.2/core-common/src/main/java/org/glassfish/jersey/filter/LoggingFilter.java">Inspired by this filter</a>
 */
@Priority(Priorities.ENTITY_CODER)
public class ClientResponseLogger implements ClientResponseFilter {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private int maxEntitySize = 1024 * 8;

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        if (responseContext.hasEntity() && LOG.isDebugEnabled()) {
            StringBuilder sb = new StringBuilder(300);
            Charset cs = StandardCharsets.UTF_8;
            InputStream is = logInboundEntity(sb, responseContext.getEntityStream(), cs);
            responseContext.setEntityStream(is);
            LOG.debug("Received content so far: {}", sb);
        }
    }

    private InputStream logInboundEntity(final StringBuilder b, InputStream stream, final Charset charset) throws IOException {
        if (!stream.markSupported()) {
            stream = new BufferedInputStream(stream);
        }
        stream.mark(maxEntitySize + 1);
        final byte[] entity = new byte[maxEntitySize + 1];
        final int entitySize = stream.read(entity);
        b.append(new String(entity, 0, Math.min(entitySize, maxEntitySize), charset));
        if (entitySize > maxEntitySize) {
            b.append("... (and more)");
        }
        b.append('\n');
        stream.reset();
        return stream;
    }
}
