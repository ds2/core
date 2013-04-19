/**
 * 
 */
package ds2.oss.core.interceptors;

import javax.interceptor.InvocationContext;

/**
 * @author dstrauss
 * 
 */
public class LoggerUtils {
    
    /**
     * 
     */
    public LoggerUtils() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Creates an exit message.
     * 
     * @param ic
     *            the invocation context
     * @param rc
     *            the result object
     * @return an exit message
     */
    public static String createExitHeader(final InvocationContext ic,
        final Object rc) {
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
     * Returns the header string containing the ic target, method name and
     * parameter values.
     * 
     * @param ic
     *            the invocation context
     * @return a header string
     */
    public final static String createMethodHeader(final InvocationContext ic) {
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
        if ((params != null) && (params.length > 0)) {
            
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
    
}
