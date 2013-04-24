/**
 * 
 */
package ds2.oss.core.interceptors.test;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import ds2.oss.core.interceptors.InfoLogInterceptor;

/**
 * @author dstrauss
 * 
 */
@Stateless
@Interceptors(InfoLogInterceptor.class)
public class SimpleBean {
    
    /**
     * 
     */
    public SimpleBean() {
        // TODO Auto-generated constructor stub
    }
    
    public String getSomething(final int a, final int b) {
        return "Hi, " + a + "+" + b;
    }
    
}
