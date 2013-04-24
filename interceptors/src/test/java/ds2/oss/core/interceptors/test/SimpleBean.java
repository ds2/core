/**
 * 
 */
package ds2.oss.core.interceptors.test;

import javax.ejb.Stateless;

/**
 * @author dstrauss
 * 
 */
@Stateless
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
