package ds2.oss.core.testutils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import java.lang.invoke.MethodHandles;
import java.util.Random;

/**
 * Created by deindesign on 12.04.16.
 */
@Dependent
@Priority(0)
@Alternative
public class TestUtilsProvider {
    private static final Logger LOG= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Dependent
    @Produces
    public Random createRandomizer(){
        LOG.info("Using test randomizer :)");
        Random rc=new Random(System.currentTimeMillis());
        return rc;
    }
}
