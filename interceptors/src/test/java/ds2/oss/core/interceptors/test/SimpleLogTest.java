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
package ds2.oss.core.interceptors.test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * The test case to test the interceptors.
 * 
 * @author dstrauss
 * @version 0.1
 */
public class SimpleLogTest {
    /**
     * The bean to test.
     */
    private SimpleBean sb;
    /**
     * The embedded ejb container.
     */
    private EJBContainer ejbC;
    
    /**
     * Inits the test.
     */
    public SimpleLogTest() {
        // nothing special to do
    }
    
    /**
     * Any actions to perform at class start.
     * 
     * @throws NamingException
     *             if a JNDI error occurred
     */
    @BeforeClass
    public final void onClass() throws NamingException {
        ejbC = EJBContainer.createEJBContainer();
        final Context ic = ejbC.getContext();
        sb = (SimpleBean) ic.lookup("java:global/test/SimpleBean");
    }
    
    /**
     * Any actions to perform after class.
     */
    @AfterClass
    public final void afterClass() {
        ejbC.close();
    }
    
    /**
     * Simple execution of the bean. The test must be done by checking the log
     * output.
     */
    @Test
    public final void execute1() {
        final String rc = sb.getSomething(1, 2);
        Assert.assertNotNull(rc);
    }
}
