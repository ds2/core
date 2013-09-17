package ds2.oss.core.crypto.test;

import ds2.oss.core.api.crypto.BytesProvider;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.security.Security;

/**
 * Created by dstrauss on 17.09.13.
 */
public class SecureBytesProviderTest extends AbstractInjectionEnvironment {
  private BytesProvider to;

  @BeforeClass
  public void onClass(){
    Security.addProvider(new BouncyCastleProvider());
    to=getInstance(BytesProvider.class);
  }
  @Test
  public void testCreateNegativeOr0(){
    Assert.assertNull(to.createRandomByteArray(-1));
  }
  @Test
  public void testCreate0(){
    Assert.assertNull(to.createRandomByteArray(0));
  }
  @Test
  public void testCreate1(){
    Assert.assertNotNull(to.createRandomByteArray(16));
  }
}
