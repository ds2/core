/**
 * 
 */
package ds2.oss.core.interceptors.test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author dstrauss
 * 
 */
public class SimpleLogTest {
    private SimpleBean sb;
    private EJBContainer ejbC;
    
    /**
     * 
     */
    public SimpleLogTest() {
        // TODO Auto-generated constructor stub
    }
    
    @BeforeClass
    public void onMethod() throws NamingException {
        ejbC = EJBContainer.createEJBContainer();
        Context ic = ejbC.getContext();
        sb = (SimpleBean) ic.lookup("java:global/interceptors/SimpleBean");
    }
    
    @AfterClass
    public void afterClass() {
        ejbC.close();
    }
    
    @Test
    public void execute1() {
        String rc = sb.getSomething(1, 2);
        Assert.assertNotNull(rc);
    }
}
