package ds2.oss.core.owbtests;

import org.apache.webbeans.config.WebBeansContext;
import org.apache.webbeans.spi.ContainerLifecycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import javax.enterprise.util.TypeLiteral;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Type;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dstrauss on 18.04.17.
 */
public class AbstractOwbInjectionEnvironment {
    /**
     * A lock.
     */
    private static final Lock LOCK = new ReentrantLock();

    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static ContainerLifecycle wc = null;

    /**
     * Returns an instance of the given class.
     *
     * @param c   the class
     * @param <T> the type of the bean
     * @return the instance, if found. Otherwise null.
     */
    protected <T> T getInstance(final Class<T> c) {
        LOCK.lock();
        try {
            if (wc == null) {
                LOG.warn("As the weld container is null, no CDI lookup will be done, and I will return null here for {}.", c);
                return null;
            }
            Type type;
            return (T) wc.getBeanManager().getBeans(c).iterator().next();
        } finally {
            LOCK.unlock();
        }
    }

    protected <T> T getInstance(final TypeLiteral<T> c) {
        LOCK.lock();
        try {
            if (wc == null) {
                LOG.warn("As the weld container is null, no CDI lookup will be done, and I will return null here for {}.", c);
                return null;
            }
            return null;
        } finally {
            LOCK.unlock();
        }
    }

    /**
     * Actions to perform at the end of the test suite.
     */
    @AfterSuite(alwaysRun = true)
    public void onSuiteEnd() {
        LOCK.lock();
        try {
            if (wc != null) {
                LOG.debug("Shutting down OWB");
                wc.stopApplication(null);
                wc = null;
            } else {
                LOG.warn("OWB Container is null, ignoring shutting it down as perhaps at start it has crashed.");
            }
        } finally {
            LOCK.unlock();
        }
    }

    /**
     * Actions to perform at test suite start.
     */
    @BeforeSuite(alwaysRun = true)
    public void onSuiteStart() {
        LOG.debug("Entering OWB Init");
        LOCK.lock();
        try {
            if (wc != null) {
                LOG.info("Nothing to do, ignoring");
                return;
            }
            LOG.debug("Performing some setup before starting the CDI environment..");
            performInitialSetup();
            LOG.debug("Starting init");
            wc = WebBeansContext.currentInstance().getService(ContainerLifecycle.class);
            wc.startApplication(null);
        } finally {
            LOCK.unlock();
        }
        LOG.debug("Done with init");
    }

    protected void performInitialSetup() {
        //Actually, do nothing here. If you have something to setup BEFORE starting the CDI, please put it up here
    }
}
