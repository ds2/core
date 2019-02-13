/*
 * Copyright 2019 DS/2 <dstrauss@ds-2.de>
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
    public void filter(ContainerRequestContext requestContext) {
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
