package ds2.oss.core.elasticsearch.test;

import ds2.oss.core.elasticsearch.api.CodecProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by dstrauss on 22.07.13.
 */
public class CodecProviderImplTest extends AbstractInjectionEnvironment{
  private CodecProvider to;
  @BeforeClass
  public void onClass(){
    to=getInstance(CodecProvider.class);
  }
  @Test
  public void testNull(){
    Assert.assertNull(to.findFor(null));
  }
  @Test
  public void test1(){
    Assert.assertNotNull(to.findFor(MyNews.class));
  }
}
