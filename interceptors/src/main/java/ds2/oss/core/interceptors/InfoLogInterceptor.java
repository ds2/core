/*
 * Copyright 2012-2014 Dirk Strauss
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
package ds2.oss.core.interceptors;

import java.util.HashMap;
import java.util.Map;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An interceptor for any info logging.
 * 
 * @author dstrauss
 * @version 0.1
 */
@Interceptor
@LogCallings
public class InfoLogInterceptor {
    /**
     * Creates an exit message.
     * 
     * @param ic
     *            the invocation context
     * @param rc
     *            the result object
     * @return an exit message
     */
    private static String createExitHeader(final InvocationContext ic, final Object rc) {
        final StringBuilder sb = new StringBuilder();
        sb.append("exiting method ");
        sb.append(ic.getTarget().getClass().getName());
        sb.append(".");
        sb.append(ic.getMethod().getName());
        sb.append("() with rc=");
        sb.append(rc);
        return sb.toString();
    }
    
    /**
     * Returns the header string containing the ic target, method name and parameter values.
     * 
     * @param ic
     *            the invocation context
     * @return a header string
     */
    private static String createMethodHeader(final InvocationContext ic) {
        if (ic == null) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("Entering method ");
        sb.append(ic.getTarget().getClass().getName());
        sb.append(".");
        sb.append(ic.getMethod().getName());
        sb.append("(");
        final Object[] params = ic.getParameters();
        boolean isFirst = true;
        if (params != null && params.length > 0) {
            
            for (Object param : params) {
                if (!isFirst) {
                    sb.append(", ");
                }
                sb.append(param);
                if (param != null) {
                    sb.append(" [");
                    sb.append(param.getClass().getName());
                    sb.append("]");
                }
                isFirst = false;
            }
        }
        sb.append(")");
        return sb.toString();
    }
    
    /**
     * Returns the logger for the given target class.
     * 
     * @param ic
     *            the invocation contex to get the target from
     * @return a logger.
     */
    private static Logger getLoggerForTarget(final InvocationContext ic) {
        final String className = ic.getTarget().getClass().getName();
        synchronized (loggerMap) {
            Logger rc = loggerMap.get(className);
            if (rc == null) {
                rc = LoggerFactory.getLogger(className);
                LOG.info("Putting logger for {} into map", className);
                loggerMap.put(className, rc);
            }
            return rc;
        }
    }
    
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(InfoLogInterceptor.class);
    
    /**
     * A map with loggers.
     */
    private static Map<String, Logger> loggerMap = new HashMap<>();
    
    /**
     * Logs a given call to/from a method.
     * 
     * @param ic
     *            the invocation context.
     * @return the result of the invocation context.
     * @throws Exception
     *             the exception that is possibly thrown by the invocation context.
     */
    @SuppressWarnings("static-method")
    @AroundInvoke
    public Object intercept(final InvocationContext ic) throws Exception {
        final Logger log = getLoggerForTarget(ic);
        if (log.isInfoEnabled()) {
            log.info(createMethodHeader(ic));
        }
        Object rc = null;
        try {
            rc = ic.proceed();
        } catch (final Exception e) {
            log.info("An error occurred: {}", e.getLocalizedMessage());
            throw e;
        } finally {
            if (log.isInfoEnabled()) {
                log.info(createExitHeader(ic, rc));
            }
        }
        return rc;
    }
}
