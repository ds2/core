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
package ds2.oss.core.jee.rest.client.filters;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Created by dstrauss on 25.04.17.
 */
@Priority(Priorities.AUTHENTICATION)
public class BasicAuthenticationFilter implements ClientRequestFilter {
    private String base64Token;

    public BasicAuthenticationFilter(String base64Token) {
        this.base64Token = base64Token;
    }

    public BasicAuthenticationFilter(String username, char[] pw) {
        String tmpStr = username + ":" + new String(pw);
        this.base64Token = Base64.getEncoder().encodeToString(tmpStr.getBytes(StandardCharsets.UTF_8));
        tmpStr = "";
    }

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        requestContext.getHeaders().putSingle("Authorization", "Basic " + base64Token);
    }
}
