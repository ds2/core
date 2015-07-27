/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.webtools;

import java.io.IOException;
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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //silently ignored
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        MDC.put("requestServerName", request.getServerName());
        MDC.put("requestServerPort", "" + request.getServerPort());
        MDC.put("requestServerScheme", request.getScheme());
        MDC.put("requestSecure", "" + request.isSecure());
        if (HttpServletRequest.class.isAssignableFrom(request.getClass())) {
            HttpServletRequest req = (HttpServletRequest) request;
            MDC.put("sessionId", req.getSession().getId());
        }
    }

    @Override
    public void destroy() {
        //silently ignored
    }

}
