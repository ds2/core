package ds2.oss.core.webtools.io;

import java.io.UnsupportedEncodingException;
import java.nio.CharBuffer;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CharConverterImplTest {
    /**
     * The test object.
     */
    private CharConverterImpl to = null;
    
    @Test
    public void convertchar() throws UnsupportedEncodingException {
        byte[] b = to.convertChars('a', 'b');
        Assert.assertNotNull(b);
        String s = new String(b, "utf-8");
        Assert.assertEquals(s, "ab");
    }
    
    @Test
    public void convertCharSequence() throws UnsupportedEncodingException {
        char[] cArray = { 'a', 'b', 'c', 'd' };
        byte[] b = to.convertCharSequence(cArray, 1, 2);
        String s = new String(b, "utf-8");
        Assert.assertEquals(s, "bc");
    }
    
    @Test
    public void convertIntChar() throws UnsupportedEncodingException {
        byte[] b = to.convertIntChar('a');
        Assert.assertNotNull(b);
        String s = new String(b, "utf-8");
        Assert.assertEquals(s, "a");
    }
    
    @BeforeMethod
    public void onMethodStart() {
        to = new CharConverterImpl();
    }
    
    @Test
    public void testString1() throws UnsupportedEncodingException {
        String s1 = "\u00e4t";
        char[] cA = s1.toCharArray();
        Assert.assertEquals(cA.length, 2);
        byte[] b = s1.getBytes("utf-8");
        Assert.assertEquals(b.length, 3);
    }
    
    @Test
    public void testString2() throws UnsupportedEncodingException {
        char[] cA = { '\u00e4' };
        Assert.assertEquals(cA.length, 1);
        CharBuffer cb = CharBuffer.wrap(cA);
        byte[] b = to.convertBufferContent(cb, 1);
        Assert.assertEquals(b.length, 3);
    }
}
