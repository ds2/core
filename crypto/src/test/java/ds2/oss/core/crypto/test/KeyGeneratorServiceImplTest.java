package ds2.oss.core.crypto.test;

import ds2.oss.core.api.crypto.KeyGeneratorNames;
import ds2.oss.core.api.crypto.KeyGeneratorService;
import ds2.oss.core.crypto.KeyGeneratorServiceImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.crypto.SecretKey;

/**
 * Created by dstrauss on 21.08.13.
 */
public class KeyGeneratorServiceImplTest {
  private KeyGeneratorService to;
  @BeforeClass
  public void onClass(){
    to=new KeyGeneratorServiceImpl();
  }
  @Test
  public void testAes1(){
    Assert.assertNotNull(to.generate(256, KeyGeneratorNames.AES));
  }
  @Test
  public void testAesPw1(){
    SecretKey key=to.generate("hello", KeyGeneratorNames.AES);
    Assert.assertNotNull(key);
    Assert.assertEquals(to.generate("hello", KeyGeneratorNames.AES),key);
  }
}
