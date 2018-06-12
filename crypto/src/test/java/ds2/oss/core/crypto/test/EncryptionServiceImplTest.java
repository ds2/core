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
package ds2.oss.core.crypto.test;

import ds2.oss.core.api.crypto.*;
import ds2.oss.core.testutils.AbstractInjectionEnvironment;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;

/**
 * The encryption service test.
 *
 * @author dstrauss
 * @version 0.3
 */
public class EncryptionServiceImplTest extends AbstractInjectionEnvironment {
    /**
     * The test object.
     */
    private EncryptionService to;
    /**
     * A key generator.
     */
    private KeyGeneratorService keygen;
    /**
     * The encoded bytes.
     */
    private EncodedContent encodedStuff;
    private SecretKey aesKey;
    /**
     * The runtime data.
     */
    private JavaRuntimeData runtime;
    /**
     * The message to encode.
     */
    private final String msg = "Hallo, W\u00e4lt!\nScheint zu funktionieren.\n\nTest.";

    /**
     * Actions to perform at start.
     */
    @BeforeClass
    public void onClass() {
        to = getInstance(EncryptionService.class);
        keygen = getInstance(KeyGeneratorService.class);
        runtime = getInstance(JavaRuntimeData.class);
    }

    /**
     * Encryption test.
     *
     * @throws UnsupportedEncodingException if an error occurred
     */
    @Test
    public void testEncrypt() throws UnsupportedEncodingException {
        aesKey = keygen.generateSecureAesKey("test", runtime.getAesMaxKeysize());
        encodedStuff = to.encode(aesKey, Ciphers.AES, msg.getBytes("utf-8"));
        Assert.assertNotNull(encodedStuff);
    }

    @Test
    public void testEncryptWithDifferentInitVector() throws UnsupportedEncodingException {
        final SecretKey sk = keygen.generateSecureAesKey("test", runtime.getAesMaxKeysize());
        EncodedContent enc1 = to.encode(sk, Ciphers.AES, msg.getBytes("utf-8"));
        EncodedContent enc2 = to.encode(sk, Ciphers.AES, msg.getBytes("utf-8"));
        Assert.assertNotEquals(enc1, enc2);
    }

    /**
     * Decode test.
     *
     * @throws UnsupportedEncodingException if the encoding is unknown
     */
    @Test(dependsOnMethods = "testEncrypt")
    public void testDecrypt() throws UnsupportedEncodingException {
        //final SecretKey sk = keygen.generateSecureAesKey("test", runtime.getAesMaxKeysize());
        final byte[] decoded = to.decode(aesKey, Ciphers.AES, encodedStuff);
        Assert.assertNotNull(decoded);
        final String s = new String(decoded, "utf-8");
        Assert.assertEquals(s, msg);
    }
}
