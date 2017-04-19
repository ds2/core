/*
 * Copyright 2012-2015 Dirk Strauss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ds2.oss.core.jee.rest;

import ds2.oss.core.api.annotations.LogResourceRequests;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;

/**
 * Created by deindesign on 18.12.15.
 */
@Provider
@LogResourceRequests
@Priority(Priorities.HEADER_DECORATOR)
public class ResourceRequestLogger implements ContainerRequestFilter {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        StringBuilder sb = new StringBuilder(600);
        sb.append(requestContext.getRequest().getMethod()).append(" ");
        requestContext.getUriInfo();
        String path = requestContext.getUriInfo().getPath();
        sb.append(path);
        MultivaluedMap<String, String> headers = requestContext.getHeaders();
        sb.append("Headers:\n");
        for (Map.Entry<String, List<String>> header : headers.entrySet()) {
            sb.append(header.getKey()).append("=").append(header.getValue());
            sb.append("\n");
        }
        LOG.info("Incoming request: {}", sb);
    }
}
