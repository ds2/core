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
package ds2.oss.core.base.impl.test;

import ds2.core.testonly.utils.AbstractInjectionEnvironment;
import ds2.oss.core.api.Base64Codec;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Base64;

import static org.testng.Assert.assertEquals;

/**
 * The base64 test.
 *
 * @author dstrauss
 * @version 0.4
 */
public class Base64KonverterTest extends AbstractInjectionEnvironment {
    /**
     * The contract to test.
     */
    private Base64Codec to;
    /**
     * The charset to use for byte conversion.
     */
    private Charset cs;
    /**
     * The message to convert.
     */
    private static final String CS = "h\u00e4llo";
    private Base64.Encoder jEncoder;
    private Base64.Decoder jDecoder;

    @BeforeClass
    public void onInit() {
        to = getInstance(Base64Codec.class);
        //to = new Base64Konverter();
        cs = Charset.forName("utf-8");
        jEncoder = Base64.getEncoder();
        jDecoder = Base64.getDecoder();
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
        assertEquals(s2, CS);
    }

    @Test
    public void encode1() {
        final byte[] b = CS.getBytes(cs);
        final String t = to.encode(b);
        assertEquals(t, "aMOkbGxv");
        assertEquals(jEncoder.encodeToString(b), t);
    }

    @Test
    public void encode2() {
        final byte[] b = "Das ist ein etwas \u00e4ngerer Text mit Umlauten usw.,".getBytes(cs);
        final String t = to.encode(b);
        assertEquals(t, "RGFzIGlzdCBlaW4gZXR3YXMgw6RuZ2VyZXIgVGV4dCBtaXQgVW1sYXV0ZW4gdXN3Liw=");
        assertEquals(jEncoder.encodeToString(b), t);
    }

    @Test(enabled = false)
    public void testContains1() {
        // final byte pos = to.holeAlphabetPosFuerChar('a');
        // Assert.assertEquals(pos, 26);
    }

    @Test
    public void decodeToFile() throws IOException {
        String b64Str = "8c2d2737";
        byte[] data = to.decode(b64Str.toCharArray());
        Path path = Files.write(File.createTempFile("ds2-oss-core-base-b64", ".bin").toPath(), data, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        System.out.println("Data is written to " + path);
        Assert.assertTrue(Files.exists(path));
    }
}
