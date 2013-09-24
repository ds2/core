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
package ds2.oss.core.crypto.test;

import java.lang.annotation.Annotation;
import java.util.Set;

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
    
    @BeforeSuite(alwaysRun = true)
    public void onSuite() {
        wc = weld.initialize();
    }
    
    @AfterSuite(alwaysRun = true)
    public void onSuiteEnd() {
        weld.shutdown();
        weld = null;
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
        return wc.instance().select(c).get();
    }
    
    public static <T> T getInstance(final Class<T> c, final Annotation... annotations) {
        Set<Bean<?>> beans = wc.getBeanManager().getBeans(c, annotations);
        if ((beans != null) && !beans.isEmpty()) {
            for (Bean b : beans) {
                System.out.println("Bean is " + b);
            }
        }
        return wc.instance().select(c, annotations).get();
    }
}