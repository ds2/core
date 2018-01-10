package ds2.oss.core.dbtools;

import ds2.oss.core.dbtools.modules.LifeCycleAwareModule;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MetamodelGenTest {
    @Test
    public void testLoadClass() {
        try {
            Assert.assertNotNull(Class.forName(LifeCycleAwareModule.class.getName()));
            Assert.assertNotNull(Class.forName(LifeCycleAwareModule.class.getName() + "_"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace(System.err);
            Assert.fail("Metamodel not generated!");
        }
    }
}
