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
package ds2.oss.core.base.impl.test;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ds2.oss.core.base.impl.CollectionBuilder;
import ds2.oss.core.base.impl.CollectionBuilderContract;

/**
 * Simple testcase for the collection builder.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class CollectionBuilderTest {
    @Test
    public void test1() {
        CollectionBuilderContract<List<String>, String> cb = CollectionBuilder.newList(String.class);
        List<String> l = cb.build();
        Assert.assertNotNull(l);
    }
    
    @Test
    public void testAllowNotNull() {
        CollectionBuilderContract<List<String>, String> s = CollectionBuilder.create(new ArrayList());
        s.allowNull(false).add("Hello").add(null).add("World");
        List<String> l = s.build();
        Assert.assertEquals(l.size(), 2);
    }
}
