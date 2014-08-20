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
/**
 * 
 */
package ds2.oss.core.base.impl.test;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Simple sort tests.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class DummyIntVergleichTest {
    
    private int compareInt(final int a, final int b) {
        int rc = 0;
        // a-b
        if (a > b) {
            rc = 0x2;
        } else if (a < b) {
            rc = 0x1;
        }
        return rc;
    }
    
    @Test
    public void testRule1() {
        Assert.assertEquals(compareInt(1, 1), 0);
        Assert.assertEquals(compareInt(2, 2), 0);
        Assert.assertEquals(compareInt(535, 535), 0);
    }
    
    @Test
    public void testRule2() {
        Assert.assertEquals(compareInt(1, 2), 1);
        Assert.assertEquals(compareInt(1, 5), 1);
    }
    
    @Test
    public void testRule3() {
        Assert.assertEquals(compareInt(2, 1), 2);
        Assert.assertEquals(compareInt(5, 1), 2);
    }
    
}
