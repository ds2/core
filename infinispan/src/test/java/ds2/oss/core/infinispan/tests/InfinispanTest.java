/**
 * 
 */
package ds2.oss.core.infinispan.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author dstrauss
 *
 */
@Test
public class InfinispanTest extends AbstractInjectionEnvironment {
    
    private MyOptionStoreServiceImpl to;
    
    @BeforeClass
    public void onLoad() {
        to = getInstance(MyOptionStoreServiceImpl.class);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNull() {
        to.store(null);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testOptionOhneKey() {
        to.store(new MyOption());
    }
    
    @Test
    public void testOptionValid() {
        MyOption option = new MyOption();
        option.setKey("k1");
        option.setVal("val");
        to.store(option);
    }
}
