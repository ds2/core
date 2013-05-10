/**
 * 
 */
package ds2.oss.core.elasticsearch.test;

import java.util.Date;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import ds2.oss.core.elasticsearch.api.ElasticSearchService;

/**
 * Tests for the ES service.
 * 
 * @author dstrauss
 * @version 0.2
 */
public class LocalEsTest {
    /**
     * The classpath scanner.
     */
    private static Weld weld = new Weld();
    /**
     * The container.
     */
    private static WeldContainer wc;
    /**
     * The test object.
     */
    private ElasticSearchService to;
    /**
     * The codec.
     */
    private NewsCodec newsCodec;
    
    /**
     * Inits the test.
     */
    public LocalEsTest() {
        // TODO Auto-generated constructor stub
    }
    
    @BeforeSuite
    public void onSuite() {
        wc = weld.initialize();
    }
    
    @AfterSuite
    public void onSuiteEnd() {
        weld.shutdown();
    }
    
    public static <T> T getInstance(final Class<T> c) {
        return wc.instance().select(c).get();
    }
    
    @BeforeMethod
    public void onMethod() {
        to = getInstance(ElasticSearchService.class);
        newsCodec = getInstance(NewsCodec.class);
    }
    
    @Test
    public void testExistense() {
        Assert.assertNotNull(to);
    }
    
    @Test
    public void testPutNull() {
        to.put(null, null);
    }
    
    @Test
    public void testPut1() {
        MyNews mn = new MyNews();
        mn.setAuthor("dstrauss");
        mn.setMsg("This is a simple test message.");
        mn.setPostDate(new Date());
        mn.setTitle("Hello, world");
        to.put(mn, newsCodec);
    }
}
