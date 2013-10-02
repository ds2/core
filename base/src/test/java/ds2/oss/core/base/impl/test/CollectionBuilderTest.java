package ds2.oss.core.base.impl.test;

import ds2.oss.core.base.impl.CollectionBuilder;
import org.testng.Assert;

import java.util.List;

/**
 * Created by dstrauss on 02.10.13.
 */
public class CollectionBuilderTest {
  public void test1(){
    CollectionBuilder cb=CollectionBuilder.create(List.class);
    List<String> l=cb.build();
    Assert.assertNotNull(l);
  }
}
