package ds2.oss.core.base.impl.test;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Some options test.
 * @author dstrauss
 * @version 0.3
 */
public class OptionsTest {
  @Test
  public void testInit(){
    Assert.assertNotNull(KnownOptions.SERVERLIST.getIdentifier());
  }
}
