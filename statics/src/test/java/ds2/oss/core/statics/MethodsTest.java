package ds2.oss.core.statics;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by dstrauss on 08.06.16.
 */
public class MethodsTest {
    @Test
    public void testNumber1(){
        Assert.assertNull(Methods.parseNumber(null));
    }
    @Test
    public void testNumber2(){
        Number n=Methods.parseNumber("23");
        Assert.assertNotNull(n);
        Assert.assertEquals(n.intValue(),23);
    }

}
