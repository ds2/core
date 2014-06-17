/*
 * Copyright 2012-2014 Dirk Strauss
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

import java.io.UnsupportedEncodingException;
import java.security.Security;

import javax.crypto.SecretKey;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ds2.oss.core.api.crypto.Ciphers;
import ds2.oss.core.api.crypto.EncodedContent;
import ds2.oss.core.api.crypto.EncryptionService;
import ds2.oss.core.api.crypto.KeyGeneratorService;

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
    /**
     * The message to encode.
     */
    private String msg = "Hallo, W\u00e4lt!\nScheint zu funktionieren.\n\nTest.";
    
    /**
     * Actions to perform at start.
     */
    @BeforeClass
    public void onClass() {
        Security.insertProviderAt(new BouncyCastleProvider(), 1);
        to = getInstance(EncryptionService.class);
        keygen = getInstance(KeyGeneratorService.class);
    }
    
    /**
     * Encryption test.
     * 
     * @throws UnsupportedEncodingException
     *             if an error occurred
     */
    @Test
    public void testEncrypt() throws UnsupportedEncodingException {
        final SecretKey sk = keygen.generateSecureAesKey("test");
        encodedStuff = to.encode(sk, Ciphers.AES, msg.getBytes("utf-8"));
        Assert.assertNotNull(encodedStuff);
    }
    
    /**
     * Decode test.
     * 
     * @throws UnsupportedEncodingException
     *             if the encoding is unknown
     */
    @Test(dependsOnMethods = "testEncrypt")
    public void testDecrypt() throws UnsupportedEncodingException {
        final SecretKey sk = keygen.generateSecureAesKey("test");
        final byte[] decoded = to.decode(sk, Ciphers.AES, encodedStuff);
        Assert.assertNotNull(decoded);
        final String s = new String(decoded, "utf-8");
        Assert.assertEquals(s, msg);
    }
}
