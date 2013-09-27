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
package ds2.oss.core.elasticsearch.test;

import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.enterprise.inject.spi.Bean;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
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
    
    @BeforeSuite(alwaysRun = true)
    public void onSuite() {
        try {
            LOCK.lock();
            if (weld == null) {
                weld = new Weld();
            }
            wc = weld.initialize();
        } finally {
            LOCK.unlock();
        }
    }
    
    @AfterSuite(alwaysRun = true)
    public void onSuiteEnd() {
        try {
            LOCK.lock();
            weld.shutdown();
            weld = null;
        } finally {
            LOCK.unlock();
        }
    }
    
    /**
     * Returns an instance of the given class.
     * 
     * @param c
     *            the class
     * 
     * @return an instance
     */
    public static <T> T getInstance(final Class<T> c) {
        try {
            LOCK.lock();
            return wc.instance().select(c).get();
        } finally {
            LOCK.unlock();
        }
    }
    
    public static <T> T getInstance(final Class<T> c, final Annotation... annotations) {
        try {
            LOCK.lock();
            Set<Bean<?>> beans = wc.getBeanManager().getBeans(c, annotations);
            if ((beans != null) && !beans.isEmpty()) {
                for (Bean b : beans) {
                    System.out.println("Bean is " + b);
                }
            }
            return wc.instance().select(c, annotations).get();
        } finally {
            LOCK.unlock();
        }
    }
}
