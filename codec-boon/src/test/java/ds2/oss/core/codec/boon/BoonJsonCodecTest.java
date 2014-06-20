/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.codec.boon;

import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.JsonCodec;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author dstrauss
 */
public class BoonJsonCodecTest extends AbstractInjectionEnvironment {

    private JsonCodec to;

    @BeforeClass
    public void onClass() {
        to = getInstance(JsonCodec.class);
    }

    @Test
    public void testEncodeNull() throws CoreException {
        Assert.assertEquals(to.encode(null), "");
    }

    @Test
    public void testEncode1() throws CoreException, MalformedURLException {
        Complex1 c = new Complex1();
        c.setCreated(new Date(123L));
        c.setHomepage(new URL("http://www.bla.test"));
        c.setMsg("Hello, World");
        c.setNumber(23);
        c.setState(MyEnum.VAL1);
        Assert.assertEquals(to.encode(c), "{\"number\":23,\"msg\":\"Hello, World\",\"homepage\":\"http://www.bla.test\",\"created\":123,\"state\":\"VAL1\"}");
    }
}
