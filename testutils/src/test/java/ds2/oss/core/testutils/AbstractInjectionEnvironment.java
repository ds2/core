/*
 * Copyright 2012-2015 Dirk Strauss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ds2.oss.core.testutils;

import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandles;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.enterprise.inject.spi.Bean;
import javax.enterprise.util.TypeLiteral;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * The injection env. Basically the same as the WeldWrapper.
 *
 * @author dstrauss
 * @version 0.2
 */
public abstract class AbstractInjectionEnvironment {

    /**
     * A lock.
     */
    private static final Lock LOCK = new ReentrantLock();

    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * The container that manages the instances.
     */
    private static WeldContainer wc;

    /**
     * The classpath scanner.
     */
    private static Weld weld=new Weld();

    /**
     * Returns an instance of the given class.
     *
     * @param c the class
     *
     * @return the instance, if found. Otherwise null.
     * @param <T> the type of the bean
     */
    protected <T> T getInstance(final Class<T> c) {
        LOCK.lock();
        try {
            if(wc==null){
                LOG.warn("As the weld container is null, no CDI lookup will be done, and I will return null here for {}.", c);
                return null;
            }
            return wc.instance().select(c).get();
        } finally {
            LOCK.unlock();
        }
    }

    protected <T> T getInstance(final TypeLiteral<T> c) {
        LOCK.lock();
        try {
            if(wc==null){
                LOG.warn("As the weld container is null, no CDI lookup will be done, and I will return null here for {}.", c);
                return null;
            }
            return wc.instance().select(c).get();
        } finally {
            LOCK.unlock();
        }
    }

    /**
     * A convenience method to return the used weld container.
     *
     * @return the weld container
     */
    public static WeldContainer getWeldContainer() {
        return wc;
    }

    /**
     * Returns an instance with the given annotation data.
     *
     * @param <T> the type to return
     * @param c the target class to search for
     * @param annotations some annotations to find the specific CDI bean
     * @return the found bean, or null if an error occurred
     */
    protected <T> T getInstance(final Class<T> c, final Annotation... annotations) {
        try {
            LOCK.lock();
            if(wc==null){
                LOG.warn("As the weld container is null, no CDI lookup will be done, and I will return null here for {}.", c);
                return null;
            }
            Set<Bean<?>> beans = wc.getBeanManager().getBeans(c, annotations);
            if (beans != null && !beans.isEmpty()) {
                beans.stream().forEach((b) -> {
                    LOG.debug("Bean is {}", b);
                });
            }
            return wc.instance().select(c, annotations).get();
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
                if(wc!=null){
                    LOG.debug("Shutting down Weld");
                    weld.shutdown();
                    wc = null;
                } else {
                    LOG.warn("Weld Container is null, ignoring shutting it down as perhaps at start it has crashed.");
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
        LOG.debug("Entering Weld Init");
        LOCK.lock();
        try {
            if (wc != null) {
                LOG.info("Nothing to do, ignoring");
                return;
            }
            LOG.debug("Performing some setup before starting the CDI environment..");
            performInitialSetup();
            LOG.debug("Starting init");
            wc = weld.initialize();
        } finally {
            LOCK.unlock();
        }
        LOG.debug("Done with init");
    }

    protected void performInitialSetup() {
        //Actually, do nothing here. If you have something to setup BEFORE starting the CDI, please put it up here
    }
}
