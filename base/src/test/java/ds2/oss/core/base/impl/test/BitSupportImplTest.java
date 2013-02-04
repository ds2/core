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

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ds2.oss.core.api.BitSupport;
import ds2.oss.core.base.impl.WeldWrapper;

/**
 * A testcase for the bit support.
 * 
 * @author dstrauss
 * @version 0.4
 */
public class BitSupportImplTest extends WeldWrapper {
    /**
     * The service to test.
     */
    private BitSupport to;
    
    /**
     * Inits the test.
     */
    public BitSupportImplTest() {
        // nothing special to do
    }
    
    /**
     * Actions to perform on class start.
     */
    @BeforeClass
    public final void onClass() {
        to = getInstance(BitSupport.class);
    }
    
    /**
     * Simple test 1.
     */
    @Test
    public final void testCreateIntNull() {
        Assert.assertEquals(to.createInt(null), 0);
    }
    
    /**
     * Simple test 1.
     */
    @Test
    public final void testCreateInt1() {
        int intVal = 0xf0;
        Assert.assertEquals(intVal, 240);
        byte b = (byte) (intVal & 0xff);
        Assert.assertEquals(to.createInt(b), 0xf0);
    }
}
