package ds2.oss.core.base.impl.test;

import ds2.oss.core.base.impl.SemanticVersion;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Testcases for the semVer.
 */
public class SemanticVersionTest {
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNull(){
    SemanticVersion.parse(null);
  }
  @Test
  public void testMMP(){
    Assert.assertNotNull(SemanticVersion.parse("1.0.0"));
  }
  @Test
  public void testMMP2(){
    Assert.assertNotNull(SemanticVersion.parse("12.1.24"));
  }
  @Test
  public void testMMPPrerelease1(){
    Assert.assertNotNull(SemanticVersion.parse("12.1.24-rc.1"));
  }
  @Test
  public void testMMPPrerelease2(){
    Assert.assertNotNull(SemanticVersion.parse("12.1.24-pre"));
  }
  @Test
  public void testMMPPrerelease3(){
    Assert.assertNotNull(SemanticVersion.parse("12.1.24-pre.2"));
  }
}
