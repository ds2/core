package ds2.oss.core.jee.rest;

import ds2.oss.core.statics.Methods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.UUID;

@Priority(Priorities.AUTHENTICATION)
public class MdcServerRequestFilter implements ContainerRequestFilter {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private String requestHeaderFieldName = "X-Soa-RequestId";
    private String mdcFieldName = "SoaRequestId";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String value = requestContext.getHeaderString(requestHeaderFieldName);
        if (Methods.isBlank(value)) {
            value = UUID.randomUUID().toString();
        }
        MDC.put(mdcFieldName, value);
    }

    public String getRequestHeaderFieldName() {
        return requestHeaderFieldName;
    }

    public void setRequestHeaderFieldName(String requestHeaderFieldName) {
        this.requestHeaderFieldName = requestHeaderFieldName;
    }

    public String getMdcFieldName() {
        return mdcFieldName;
    }

    public void setMdcFieldName(String mdcFieldName) {
        this.mdcFieldName = mdcFieldName;
    }
}
