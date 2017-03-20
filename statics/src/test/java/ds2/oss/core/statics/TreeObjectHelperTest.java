package ds2.oss.core.statics;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by dstrauss on 20.03.17.
 */
public class TreeObjectHelperTest {
    @Test
    public void test1() {
        DummyDto d = new DummyDto("parent");
        DummyDto d2 = new DummyDto("parent", "child");
        TreeObjectHelper.addToTree(d, d2);
        System.out.println("Tree:\n" + TreeObjectHelper.printTree(d, 0));
        Assert.assertNotNull(d.getChildren());
        Assert.assertEquals(d.getChildren().iterator().next(), d2);
        Assert.assertEquals(d2.getParent(), d);
    }

    @Test
    public void test2() {
        DummyDto d = new DummyDto("parent");
        DummyDto d2 = new DummyDto("parent", "child");
        DummyDto d31 = new DummyDto("child", "subchild");
        DummyDto d32 = new DummyDto("child", "subchild2");
        TreeObjectHelper.addToTree(d, d2);
        TreeObjectHelper.addToTree(d, d31);
        TreeObjectHelper.addToTree(d, d32);
        System.out.println("Tree:\n" + TreeObjectHelper.printTree(d, 0));
        Assert.assertNotNull(d.getChildren());
        Assert.assertEquals(d.getChildren().iterator().next(), d2);
        Assert.assertEquals(d2.getParent(), d);
    }
}
