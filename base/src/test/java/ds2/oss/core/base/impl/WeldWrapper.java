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
/**
 * 
 */
package ds2.oss.core.base.impl;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * A weld wrapper.
 * 
 * @author dstrauss
 * @version 0.1
 */
public class WeldWrapper {
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
        // TODO Auto-generated constructor stub
    }
    
    @BeforeSuite
    public void onSuiteStart() {
        if (wc != null) {
            return;
        }
        synchronized (weld) {
            wc = weld.initialize();
        }
    }
    
    @AfterSuite
    public void afterSuite() {
        weld.shutdown();
        wc = null;
    }
    
    public static <T> T getInstance(final Class<T> c) {
        return wc.instance().select(c).get();
    }
}
