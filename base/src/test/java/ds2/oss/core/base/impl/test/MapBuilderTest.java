/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.base.impl.test;

import ds2.oss.core.base.impl.MapBuilder;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author dstrauss
 */
public class MapBuilderTest {

    @Test
    public void testNull() {
        Assert.assertNotNull(MapBuilder.createWith(String.class, String.class));
    }

    @Test
    public void test1() {
        Map<String, String> m = MapBuilder.createWith(String.class, String.class).put("a", "b").build();
        Assert.assertNotNull(m);
        Assert.assertEquals(m.size(), 1);
        Assert.assertEquals(m.get("a"), "b");
    }
}
