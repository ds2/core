package ds2.oss.core.base.impl.test;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Some options test.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class OptionsTest implements Options {
    @Test
    public void testInit() {
        Assert.assertNotNull(USERNAME);
    }
}
