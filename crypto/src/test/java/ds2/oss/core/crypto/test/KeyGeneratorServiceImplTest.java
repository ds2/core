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
package ds2.oss.core.crypto.test;

import javax.crypto.SecretKey;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ds2.oss.core.api.crypto.KeyGeneratorNames;
import ds2.oss.core.api.crypto.KeyGeneratorService;

import java.security.Security;

/**
 * Testcases for the AES keygen.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class KeyGeneratorServiceImplTest extends AbstractInjectionEnvironment {
    /**
     * The service to test.
     */
    private KeyGeneratorService to;
    
    @BeforeClass
    public void onClass() {
      Security.insertProviderAt(new BouncyCastleProvider(),1);
        to = getInstance(KeyGeneratorService.class);
    }
    
    @Test
    public void testAes1() {
        Assert.assertNotNull(to.generate(256, KeyGeneratorNames.AES));
    }
    
    @Test
    public void testAesPw1() {
        SecretKey key = to.generate("hello", KeyGeneratorNames.AES);
        Assert.assertNotNull(key);
        Assert.assertEquals(to.generate("hello", KeyGeneratorNames.AES), key);
    }
  @Test
  public void testAesPw2() {
    SecretKey key = to.generateSecure("hello");
    Assert.assertNotNull(key);
    Assert.assertEquals(to.generateSecure("hello"), key);
  }
}
