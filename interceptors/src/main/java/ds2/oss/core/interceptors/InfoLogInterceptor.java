/**
 * 
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
 * 
 */
@Interceptor
@LogCallings
public class InfoLogInterceptor {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory
        .getLogger(InfoLogInterceptor.class);
    /**
     * A map with loggers.
     */
    private static Map<String, Logger> loggerMap = new HashMap<>();
    
    /**
     * 
     */
    public InfoLogInterceptor() {
    }
    
    /**
     * Logs a given call to/from a method.
     * 
     * @param ic
     *            the invocation context.
     * @return the result of the invocation context.
     * @throws Exception
     *             the exception that is possibly thrown by the invocation
     *             context.
     */
    @SuppressWarnings("static-method")
    @AroundInvoke
    public Object intercept(final InvocationContext ic) throws Exception {
        final Logger log = getLoggerForTarget(ic);
        if (log.isInfoEnabled()) {
            log.info(LoggerUtils.createMethodHeader(ic));
        }
        Object rc = null;
        try {
            rc = ic.proceed();
        } catch (final Exception e) {
            log.info("An error occurred: {}", e.getLocalizedMessage());
            throw e;
        } finally {
            if (log.isInfoEnabled()) {
                log.info(LoggerUtils.createExitHeader(ic, rc));
            }
        }
        return rc;
    }
    
    /**
     * Returns the logger for the given target class.
     * 
     * @param ic
     *            the invocation contex to get the target from
     * @return a logger.
     */
    private final static Logger getLoggerForTarget(final InvocationContext ic) {
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
}
