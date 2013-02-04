/*
 * Copyright 2012-2013 Dirk Strauss
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
package ds2.oss.core.base.impl.test;

import java.nio.charset.Charset;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ds2.oss.core.base.impl.Base64Konverter;

/**
 * The base64 test.
 * 
 * @author dstrauss
 * @version 0.4
 */
public class Base64KonverterTest {
    /**
     * The contract to test.
     */
    private Base64Konverter to;
    /**
     * The charset to use for byte conversion.
     */
    private Charset cs;
    /**
     * The message to convert.
     */
    private static final String CS = "h\u00e4llo";
    
    @BeforeClass
    public void onInit() {
        to = new Base64Konverter();
        cs = Charset.forName("utf-8");
    }
    
    @Test
    public void decodeNull() {
        Assert.assertNull(to.decode(null));
    }
    
    @Test
    public void decode1() {
        String s1 = "aMOkbGxv";
        char[] c = s1.toCharArray();
        byte[] b = to.decode(c);
        Assert.assertNotNull(b);
        String s2 = new String(b, cs);
        Assert.assertEquals(s2, CS);
    }
    
    @Test
    public void encode1() {
        byte[] b = CS.getBytes(cs);
        String t = to.encode(b);
        Assert.assertEquals(t, "aMOkbGxv");
    }
    
    @Test
    public void testContains1() {
        byte pos = to.holeAlphabetPosFuerChar('a');
        Assert.assertEquals(pos, 26);
    }
}
