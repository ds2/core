package ds2.oss.core.webtools.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoggingInputStreamTest {
    
    private byte[] readBytesFromInputstream(final InputStream is) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            while (true) {
                byte[] buffer = new byte[5];
                int read = is.read(buffer);
                if (read < 0) {
                    break;
                }
                baos.write(buffer, 0, read);
            }
            baos.close();
            is.close();
            return baos.toByteArray();
        } catch (IOException e) {
            Assert.fail("Error when reading the file!", e);
        }
        return null;
    }
    
    @Test(dependsOnMethods = "testOrig")
    public void testLis() {
        InputStream origStream = getClass().getResourceAsStream("/header.txt");
        LoggingInputStream lis = new LoggingInputStream(origStream);
        byte[] b = readBytesFromInputstream(lis);
        Assert.assertNotNull(b);
        try {
            String s = new String(b, "utf-8");
            Assert.assertEquals(s, LoggingReaderTest.EXPECTEDSTRING);
        } catch (UnsupportedEncodingException e) {
            Assert.fail("Error when converting!", e);
        }
        try {
            String s = new String(lis.getLoggedBytes(), "utf-8");
            Assert.assertEquals(s, LoggingReaderTest.EXPECTEDSTRING);
        } catch (UnsupportedEncodingException e) {
            Assert.fail("Error when converting!", e);
        }
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNull() {
        new LoggingInputStream<InputStream>(null);
    }
    
    @Test
    public void testOrig() {
        InputStream origStream = getClass().getResourceAsStream("/header.txt");
        byte[] b = readBytesFromInputstream(origStream);
        Assert.assertNotNull(b);
        try {
            String s = new String(b, "utf-8");
            Assert.assertEquals(s, LoggingReaderTest.EXPECTEDSTRING);
        } catch (UnsupportedEncodingException e) {
            Assert.fail("Error when converting!", e);
        }
    }
}
