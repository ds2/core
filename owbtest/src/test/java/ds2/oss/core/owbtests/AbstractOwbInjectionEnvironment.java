/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package ds2.oss.core.owbtests;

import ds2.oss.core.statics.Methods;
import org.apache.webbeans.config.WebBeansContext;
import org.apache.webbeans.spi.ContainerLifecycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.util.TypeLiteral;
import java.lang.invoke.MethodHandles;
import java.util.Set;
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
            BeanManager bm = wc.getBeanManager();
            Set<Bean<?>> foundBeans = bm.getBeans(c);
            if (Methods.size(foundBeans) > 0) {
                Bean bean = foundBeans.iterator().next();
                return c.cast(bean.create(null));
            }
        } finally {
            LOCK.unlock();
        }
        return null;
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
