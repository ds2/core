/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.webtools;

import java.io.IOException;
import java.security.Principal;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.MDC;

/**
 *
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
    private Set<String> requestAttributes;
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
