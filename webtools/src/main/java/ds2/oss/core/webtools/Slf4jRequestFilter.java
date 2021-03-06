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
package ds2.oss.core.webtools;

import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;

/**
 * @author deindesign
 */
public class Slf4jRequestFilter implements Filter {

    private String serverName = "requestServerName";
    private String serverPort = "requestServerPort";
    private String serverScheme = "requestServerScheme";
    private String secure = "requestSecure";
    private String sessionId = "httpSessionId";
    private String reqUri = "requestUri";
    private String httpMethod = "httpMethod";
    private String queryString = "queryString";
    private String userPrincipal = "userPrincipal";
    private String authType = "authType";
    private String contextPath = "contextPath";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        serverName = assureContent(filterConfig, "serverNameParam", serverName);
        serverPort = assureContent(filterConfig, "serverPortParam", serverPort);
        sessionId = assureContent(filterConfig, "sessionIdParam", sessionId);
    }

    private String assureContent(FilterConfig fc, String name, String defValue) {
        String val = fc.getInitParameter(name);
        if (val == null) {
            val = defValue;
        }
        return val;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        MDC.put(serverName, request.getServerName());
        MDC.put(serverPort, "" + request.getServerPort());
        MDC.put(serverScheme, request.getScheme());
        MDC.put(secure, "" + request.isSecure());
        if (HttpServletRequest.class.isAssignableFrom(request.getClass())) {
            HttpServletRequest req = (HttpServletRequest) request;
            MDC.put(sessionId, req.getSession().getId());
            MDC.put(contextPath, req.getContextPath());
            MDC.put(httpMethod, req.getMethod());
            Principal up = req.getUserPrincipal();
            MDC.put(userPrincipal, up != null ? up.toString() : null);
            MDC.put(authType, req.getAuthType());
            MDC.put(queryString, req.getQueryString());
            MDC.put(reqUri, req.getRequestURI());
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //silently ignored
    }

}
