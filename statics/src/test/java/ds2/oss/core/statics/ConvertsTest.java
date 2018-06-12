package ds2.oss.core.statics;

import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.testng.Assert.assertEquals;

/**
 * Created by dstrauss on 23.06.17.
 */
public class ConvertsTest {
    @Test
    public void testBase64EncodeDecode1() {
        String myStr = "h\u00e4llo\u00df";
        byte[] utf8Bytes = myStr.getBytes(StandardCharsets.UTF_8);
        String Iso88591Base64Str = Base64.getEncoder().encodeToString(utf8Bytes);
        String newStr = Converts.convertBase64ToString(Iso88591Base64Str, StandardCharsets.UTF_8);
        assertEquals(newStr, myStr);
    }

    @Test
    public void testBase64EncodeDecode2() {
        String myStr = "h\u00e4llo\u00df";
        byte[] utf8Bytes = myStr.getBytes(StandardCharsets.ISO_8859_1);
        String Iso88591Base64Str = Base64.getEncoder().encodeToString(utf8Bytes);
        String newStr = Converts.convertBase64ToString(Iso88591Base64Str, StandardCharsets.ISO_8859_1);
        assertEquals(newStr, myStr);
    }
}
