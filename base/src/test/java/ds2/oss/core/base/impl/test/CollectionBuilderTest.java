package ds2.oss.core.base.impl.test;

import ds2.oss.core.base.impl.CollectionBuilder;
import ds2.oss.core.base.impl.CollectionBuilderContract;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple testcase for the collection builder.
 */
public class CollectionBuilderTest {
  @Test
  public void test1(){
    CollectionBuilderContract<List<String>,String> cb=CollectionBuilder.create(ArrayList.class);
    List<String> l=cb.build();
    Assert.assertNotNull(l);
  }
  @Test
  public void testAllowNotNull(){
    CollectionBuilderContract<List<String>,String> s=CollectionBuilder.create(new ArrayList());
    s.allowNull(false).add("Hello").add(null).add("World");
    List<String> l=s.build();
    Assert.assertEquals(l.size(),2);
  }
}
