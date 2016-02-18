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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.CharBuffer;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoggingReaderTest {
    public static final String EXPECTEDSTRING =
        "This is a long t\u00e4xt which is read by a b\u00fcffer and \u00e4 l\u00f6gger.";
    private LoggingReader<InputStreamReader> fr;
    
    @BeforeTest
    public void onInitMethod() throws UnsupportedEncodingException {
        InputStream is = getClass().getResourceAsStream("/header.txt");
        Assert.assertNotNull(is);
        InputStreamReader isr = new InputStreamReader(is, "utf-8");
        fr = new LoggingReader<InputStreamReader>(isr);
    }
    
    @Test(dependsOnMethods = "testRead1")
    public void testBuffer() {
        CharBuffer copy = CharBuffer.wrap(fr.getBuffer().toString());
        String s2 = copy.toString();
        Assert.assertEquals(s2, EXPECTEDSTRING);
    }
    
    @Test
    public void testRead1() throws IOException {
        final CharBuffer cb = CharBuffer.allocate(100);
        while (true) {
            final int r = fr.read();
            if (r < 0) {
                break;
            }
            cb.append((char) r);
        }
        fr.close();
        cb.flip();
        final String s = cb.toString();
        Assert.assertNotNull(s);
        Assert.assertEquals(s, EXPECTEDSTRING);
    }
    
    @Test(dependsOnMethods = "testRead1")
    public void testUtf8Bytes() throws UnsupportedEncodingException {
        byte[] b = fr.getUtf8Bytes();
        String s = new String(b, "utf-8");
        Assert.assertEquals(s, EXPECTEDSTRING + "\0");
    }
}
