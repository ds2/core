/**
 * 
 */
package ds2.oss.core.elasticsearch.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ds2.oss.core.elasticsearch.api.GsonCodec;
import ds2.oss.core.elasticsearch.test.dto.MyNews;

/**
 * The gson codec tests.
 * 
 * @author dstrauss
 * @version 0.2
 */
public class GsonCodecTest extends AbstractInjectionEnvironment {
    /**
     * The test object.
     */
    private GsonCodec to;
    
    @BeforeMethod(alwaysRun = true)
    public void onMethod() {
        to = getInstance(GsonCodec.class);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void serializeNull() {
        to.encode(null);
    }
    
    @Test
    public void testSerializeNews() {
        MyNews n = new MyNews();
        n.setAuthor("testuser");
        n.setMsg("Hello, world");
        n.setTitle("My Title");
        String s = to.encode(n);
        Assert.assertEquals(s, "{\"title\":\"My Title\",\"author\":\"testuser\",\"message\":\"Hello, world\"}");
    }
    
    @Test
    public void testUnserialize() {
        MyNews n =
            to.decode(MyNews.class, "{\"title\":\"My Title\",\"author\":\"testuser\",\"message\":\"Hello, world\"}");
        Assert.assertNotNull(n);
        Assert.assertEquals(n.getAuthor(), "testuser");
        Assert.assertEquals(n.getMsg(), "Hello, world");
    }
}
