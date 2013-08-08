/*
 * Copyright 2012-2013 Dirk Strauss
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
package ds2.oss.core.base.impl.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * A weld wrapper.
 * 
 * @author dstrauss
 * @version 0.1
 */
public abstract class WeldWrapper {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory
        .getLogger(WeldWrapper.class);
    /**
     * The lock.
     */
    private static final Lock lock = new ReentrantLock();
    /**
     * The weld system.
     */
    private static Weld weld = new Weld();
    
    /**
     * The weld container.
     */
    private static WeldContainer wc;
    
    /**
     * Inits the wrapper.
     */
    public WeldWrapper() {
        // nothing special to do
    }
    
    @BeforeSuite(alwaysRun = true)
    public static void onSuiteStart() {
        LOG.info("Entering Weld Init");
        lock.lock();
        try {
            if (wc != null) {
                LOG.info("Nothing to do, ignoring");
                return;
            }
            LOG.info("Starting init");
            wc = weld.initialize();
        } finally {
            lock.unlock();
        }
        LOG.info("Done with init");
    }
    
    @AfterSuite(alwaysRun = true)
    public static void afterSuite() {
        lock.lock();
        try {
            LOG.info("Shutting down Weld");
            weld.shutdown();
            wc = null;
        } finally {
            lock.unlock();
        }
    }
    
    /**
     * Returns an instance of the given class.
     * 
     * @param c
     *            the class
     * 
     * @return the instance, if found. Otherwise null.
     */
    public static <T> T getInstance(final Class<T> c) {
        lock.lock();
        try {
            return wc.instance().select(c).get();
        } finally {
            lock.unlock();
        }
    }
}
