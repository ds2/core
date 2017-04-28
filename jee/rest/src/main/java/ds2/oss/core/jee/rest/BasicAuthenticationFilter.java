package ds2.oss.core.jee.rest;

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
