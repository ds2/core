/*
 * Copyright 2018 DS/2 <dstrauss@ds-2.de>
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map.Entry;

/**
 * @author dstrauss
 */
@Priority(Priorities.HEADER_DECORATOR)
public class ClientHeaderRequestLogger implements ClientRequestFilter {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /*
     * (non-Javadoc)
     * @see javax.ws.rs.client.ClientRequestFilter#filter(javax.ws.rs.client.ClientRequestContext)
     */
    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        MultivaluedMap<String, Object> headers = requestContext.getHeaders();
        for (Entry<String, List<Object>> s : headers.entrySet()) {
            LOG.debug("Request header: {} = {}", s.getKey(), s.getValue());
        }
        LOG.debug("Request uri: {} {}", requestContext.getMethod(), requestContext.getUri());
        LOG.debug("Accepted types: {}", requestContext.getAcceptableMediaTypes());
        LOG.debug("Accepted languages: {}", requestContext.getAcceptableLanguages());
    }

}
