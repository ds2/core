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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

/**
 * @author dstrauss
 */
@Priority(Priorities.HEADER_DECORATOR)
public class ClientHeaderResponseLogger implements ClientResponseFilter {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /*
     * (non-Javadoc)
     * @see javax.ws.rs.client.ClientResponseFilter#filter(javax.ws.rs.client.ClientRequestContext,
     * javax.ws.rs.client.ClientResponseContext)
     */
    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        if(LOG.isDebugEnabled()){
            MultivaluedMap<String, String> headers = responseContext.getHeaders();
            LOG.debug("Headers received are: {}", headers);
            LOG.debug("MediaType received is {}", responseContext.getMediaType());
            LOG.debug("Any cookies received: {}", responseContext.getCookies());
            LOG.debug("Return status is: {}", responseContext.getStatusInfo());
        }
    }

}
