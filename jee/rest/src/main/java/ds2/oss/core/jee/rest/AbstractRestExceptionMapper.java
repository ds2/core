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


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.lang.invoke.MethodHandles;

/**
 * Abstract contract for catching specific exceptions.
 *
 * @see javax.ws.rs.ext.Provider
 */
public abstract class AbstractRestExceptionMapper<E extends Exception> implements ExceptionMapper<E> {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public Response toResponse(E exception) {
        LOG.debug("Got this error here: {}", exception);
        Response rc = transform(exception);
        LOG.debug("Returning this response to the client: {}", rc);
        return rc;
    }

    protected abstract Response transform(E exception);
}
