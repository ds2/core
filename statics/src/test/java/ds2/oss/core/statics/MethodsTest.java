package ds2.oss.core.statics;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by dstrauss on 08.06.16.
 */
public class MethodsTest {
    @Test
    public void testNumber1() {
        Assert.assertNull(Methods.parseNumber(null));
    }

    @Test
    public void testNumber2() {
        Number n = Methods.parseNumber("23");
        Assert.assertNotNull(n);
        Assert.assertEquals(n.intValue(), 23);
    }

    @Test
    public void compareStringNulls() {
        Assert.assertEquals(Methods.compare((String) null, (String) null), 0);
    }

    @Test
    public void compareStringNulls1() {
        Assert.assertEquals(Methods.compare("", null), -1);
    }

    @Test
    public void compareStringNulls2() {
        Assert.assertEquals(Methods.compare(null, ""), 1);
    }

    @Test
    public void compareStrings1() {
        Assert.assertEquals(Methods.compare("a", "b"), -1);
    }

    @Test
    public void compareStrings2() {
        Assert.assertEquals(Methods.compare("a", "A"), 0);
    }

}
