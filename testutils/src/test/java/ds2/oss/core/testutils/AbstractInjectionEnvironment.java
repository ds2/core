/*
 * Copyright 2012-2014 Dirk Strauss
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
     * Returns an instance of the given class.
     * 
     * @param c
     *            the class
     * 
     * @return the instance, if found. Otherwise null.
     * @param <T>
     *            the type of the bean
     */
    public static <T> T getInstance(final Class<T> c) {
        LOCK.lock();
        try {
            return wc.instance().select(c).get();
        } finally {
            LOCK.unlock();
        }
    }
    
    /**
     * Returns an instance with the given annotation data.
     * 
     * @param c
     *            the target class to search for
     * @param annotations
     *            some annotations to find the specific CDI bean
     * @return the found bean, or null if an error occurred
     */
    public static <T> T getInstance(final Class<T> c, final Annotation... annotations) {
        try {
            LOCK.lock();
            Set<Bean<?>> beans = wc.getBeanManager().getBeans(c, annotations);
            if (beans != null && !beans.isEmpty()) {
                for (Bean b : beans) {
                    LOG.debug("Bean is {}", b);
                }
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
    public static void onSuiteEnd() {
        LOCK.lock();
        try {
            LOG.debug("Shutting down Weld");
            weld.shutdown();
            wc = null;
        } finally {
            LOCK.unlock();
        }
    }
    
    /**
     * Actions to perform at test suite start.
     */
    @BeforeSuite(alwaysRun = true)
    public static void onSuiteStart() {
        LOG.debug("Entering Weld Init");
        LOCK.lock();
        try {
            if (wc != null) {
                LOG.info("Nothing to do, ignoring");
                return;
            }
            LOG.debug("Starting init");
            wc = weld.initialize();
        } finally {
            LOCK.unlock();
        }
        LOG.debug("Done with init");
    }
    
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    /**
     * The classpath scanner.
     */
    private static Weld weld = new Weld();
    
    /**
     * The container.
     */
    private static WeldContainer wc;
    
    /**
     * A lock.
     */
    private static final Lock LOCK = new ReentrantLock();
}
