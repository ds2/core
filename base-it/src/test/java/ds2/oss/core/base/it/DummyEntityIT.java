/**
 * 
 */
package ds2.oss.core.base.it;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * A dummy persistence test.
 * 
 * @author dstrauss
 * @version 0.2
 */
public class DummyEntityIT extends Arquillian {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory
        .getLogger(DummyEntityIT.class);
    /**
     * The bean to test.
     */
    @Inject
    private DummyPersistenceBean to;
    
    /**
     * INits the test.
     */
    public DummyEntityIT() {
        // TODO Auto-generated constructor stub
    }
    
    @Deployment
    public static JavaArchive createTestableDeployment() {
        final JavaArchive jar =
            ShrinkWrap
                .create(JavaArchive.class, "example.jar")
                .addPackages(true, "ds2.oss.core.base.it")
                .addAsManifestResource("test-persistence.xml",
                    "persistence.xml")
                // Enable CDI
                .addAsManifestResource(EmptyAsset.INSTANCE,
                    ArchivePaths.create("beans.xml"));
        
        LOG.info(jar.toString(Formatters.VERBOSE));
        return jar;
    }
    
    @Test
    public void testStore1() {
        final DummyEntity e1 = new DummyEntity();
        to.persist(e1);
        Assert.assertNotNull(e1.getId());
    }
}
