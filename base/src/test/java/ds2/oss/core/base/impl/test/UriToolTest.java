/**
 * 
 */
package ds2.oss.core.base.impl.test;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import org.testng.Assert;
import org.testng.annotations.Test;

import ds2.oss.core.base.impl.UriTool;

/**
 * Tests for the uri tool.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class UriToolTest {
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNull() {
        UriTool.createFrom((URI) null);
    }
    
    @Test
    public void testConvert1() throws UnsupportedEncodingException, URISyntaxException {
        UriTool u = UriTool.createFrom("http://www.test.bla/baumkuchen");
        Assert.assertNotNull(u);
        Assert.assertEquals(u.build().toString(), "http://www.test.bla/baumkuchen");
    }
    
    @Test
    public void testConvert2() throws UnsupportedEncodingException, URISyntaxException {
        final UriTool u = UriTool.createFrom("http://www.test.bla/baumkuchen?bla=test");
        Assert.assertNotNull(u);
        Assert.assertEquals(u.build().toString(), "http://www.test.bla/baumkuchen?bla=test");
    }
    
    @Test
    public void testChangeVal1() throws UnsupportedEncodingException, URISyntaxException {
        final UriTool u = UriTool.createFrom("http://www.test.bla/baumkuchen?bla=test");
        Assert.assertNotNull(u);
        u.setQueryParam("bla", "bla");
        Assert.assertEquals(u.build().toString(), "http://www.test.bla/baumkuchen?bla=bla");
    }
    
    @Test
    public void testChangeVal2() throws UnsupportedEncodingException, URISyntaxException {
        final UriTool u = UriTool.createFrom("http://www.test.bla/baumkuchen?bla=test");
        Assert.assertNotNull(u);
        u.setQueryParam("a", "23");
        Assert.assertEquals(u.build().toString(), "http://www.test.bla/baumkuchen?a=23&bla=test");
    }
    @Test
    public void testChangeVal3() throws UnsupportedEncodingException, URISyntaxException {
        final UriTool u = UriTool.createFrom("http://www.test.bla/baumkuchen?bla=test");
        Assert.assertNotNull(u);
        u.addQueryParam("bla", "bla");
        Assert.assertEquals(u.build().toString(), "http://www.test.bla/baumkuchen?bla=test&bla=bla");
    }
    
}
