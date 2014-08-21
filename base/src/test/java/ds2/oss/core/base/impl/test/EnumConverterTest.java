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
package ds2.oss.core.base.impl.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import ds2.oss.core.api.EntryStates;
import ds2.oss.core.base.impl.NumericalEnumConverter;

/**
 * Enum converter test.
 * 
 * @author dstrauss
 * @version 0.1
 */
public class EnumConverterTest {
    /**
     * The test object.
     */
    private final NumericalEnumConverter<EntryStates> to = new NumericalEnumConverter<>(EntryStates.class);
    
    /**
     * Inits the test.
     */
    public EnumConverterTest() {
        // nothing special to do
    }
    
    @Test
    public void getId() {
        final int v = to.getValue(EntryStates.ACTIVE);
        Assert.assertEquals(v, 1);
    }
    
    @Test
    public void getIdNull() {
        final int v = to.getValue(null);
        Assert.assertEquals(v, -1);
    }
    
    @Test
    public void getEnum() {
        final EntryStates s = to.getEnumByReflection(1, "getById");
        Assert.assertEquals(s, EntryStates.ACTIVE);
    }
    
    @Test
    public void getEnum2() {
        final EntryStates s = to.getEnumByReflection(3, "getById");
        Assert.assertEquals(s, EntryStates.DELETED);
    }
}
