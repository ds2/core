package ds2.oss.core.base.impl.test;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ds2.oss.core.base.impl.CollectionBuilder;
import ds2.oss.core.base.impl.CollectionBuilderContract;

/**
 * Simple testcase for the collection builder.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class CollectionBuilderTest {
    @Test
    public void test1() {
        CollectionBuilderContract<List<String>, String> cb = CollectionBuilder.create(ArrayList.class);
        List<String> l = cb.build();
        Assert.assertNotNull(l);
    }
    
    @Test
    public void testAllowNotNull() {
        CollectionBuilderContract<List<String>, String> s = CollectionBuilder.create(new ArrayList());
        s.allowNull(false).add("Hello").add(null).add("World");
        List<String> l = s.build();
        Assert.assertEquals(l.size(), 2);
    }
}
