/**
 * 
 */
package ds2.oss.core.elasticsearch.test;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

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
    
    /**
     * Serializer test.
     */
    @Test
    public void testSerializeNews() {
        final MyNews n = new MyNews();
        n.setAuthor("testuser");
        n.setMsg("Hello, world");
        n.setTitle("My Title");
        final Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.GERMANY);
        cal.set(Calendar.YEAR, 2013);
        cal.set(Calendar.MONTH, Calendar.JULY);
        cal.set(Calendar.DAY_OF_MONTH, 13);
        cal.set(Calendar.HOUR_OF_DAY, 19);
        cal.set(Calendar.MINUTE, 26);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        n.setPostDate(cal.getTime());
        final String s = to.encode(n);
        Assert
            .assertEquals(
                s,
                "{\"title\":\"My Title\",\"author\":\"testuser\",\"message\":\"Hello, world\",\"postDate\":\"2013-07-13T21:26:00.000+0200\"}");
    }
    
    /**
     * Unserialize test.
     */
    @Test
    public void testUnserialize() {
        final MyNews n =
            to.decode(
                MyNews.class,
                "{\"title\":\"My Title\",\"author\":\"testuser\",\"message\":\"Hello, world\",\"postDate\":\"2013-07-13T21:26:00.000+0200\"}");
        Assert.assertNotNull(n);
        Assert.assertEquals(n.getAuthor(), "testuser");
        Assert.assertEquals(n.getMsg(), "Hello, world");
    }
}
