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

import ds2.oss.core.api.crypto.Ciphers;
import ds2.oss.core.api.crypto.EncryptionService;
import ds2.oss.core.api.crypto.KeyGeneratorService;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.security.Security;

/**
 * Created by dstrauss on 16.09.13.
 */
public class EncryptionServiceImplTest extends  AbstractInjectionEnvironment{
  private EncryptionService to;
  private KeyGeneratorService keygen;
  private byte[] encodedStuff;
  private String msg="Hallo, W\u00e4lt!\nScheint zu funktionieren.\n\nTest.";

  @BeforeClass
  public void onClass(){
    Security.insertProviderAt(new BouncyCastleProvider(), 1);
    to=getInstance(EncryptionService.class);
    keygen=getInstance(KeyGeneratorService.class);
  }
  @Test
  public void testEncrypt() throws UnsupportedEncodingException {
    SecretKey sk=keygen.generateSecure("test");
    encodedStuff=to.encode(sk, Ciphers.AES, msg.getBytes("utf-8"));
    Assert.assertNotNull(encodedStuff);
  }
  @Test(dependsOnMethods = "testEncrypt")
  public void testDecrypt() throws UnsupportedEncodingException {
    SecretKey sk=keygen.generateSecure("test");
    byte[] decoded=to.decode(sk,Ciphers.AES,encodedStuff);
    Assert.assertNotNull(decoded);
    String s=new String(decoded, "utf-8");
    Assert.assertEquals(s, msg);
  }
}
