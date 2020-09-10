/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
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
package ds2.oss.core.jee.rest.tests;

import ds2.oss.core.jee.rest.client.AbstractJaxRsClient;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.net.SocketTimeoutException;

/**
 * Created by dstrauss on 21.02.17.
 */
@SessionScoped
public class MySessionScopedService extends AbstractJaxRsClient<MyJaxException> implements Serializable {
    @Inject
    private Client client;

    @Override
    protected MyJaxException getExceptionOnClientAccess(Exception e) {
        if (e instanceof SocketTimeoutException) {
            return new MyJaxException(1);
        }
        return new MyJaxException(2, e);
    }

    @Override
    protected void handleError(Response response, MediaType thisType) throws MyJaxException {
        throw new MyJaxException(3);
    }
}
