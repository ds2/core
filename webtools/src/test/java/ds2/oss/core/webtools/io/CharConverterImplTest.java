/*
 * Copyright 2012-2015 Dirk Strauss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
