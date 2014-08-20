/**
 * 
 */
package ds2.oss.core.base.impl.test;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Simple sort tests.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class DummyIntVergleichTest {
    
    private int compareInt(final int a, final int b) {
        int rc = 0;
        // a-b
        if (a > b) {
            rc = 0x2;
        } else if (a < b) {
            rc = 0x1;
        }
        return rc;
    }
    
    @Test
    public void testRule1() {
        Assert.assertEquals(compareInt(1, 1), 0);
        Assert.assertEquals(compareInt(2, 2), 0);
        Assert.assertEquals(compareInt(535, 535), 0);
    }
    
    @Test
    public void testRule2() {
        Assert.assertEquals(compareInt(1, 2), 1);
        Assert.assertEquals(compareInt(1, 5), 1);
    }
    
    @Test
    public void testRule3() {
        Assert.assertEquals(compareInt(2, 1), 2);
        Assert.assertEquals(compareInt(5, 1), 2);
    }
    
}
