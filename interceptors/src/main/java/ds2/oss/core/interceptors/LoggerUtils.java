/*
 * Copyright 2012-2013 Dirk Strauss
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
