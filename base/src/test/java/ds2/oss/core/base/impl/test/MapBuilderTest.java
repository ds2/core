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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.base.impl.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import ds2.oss.core.base.impl.MapBuilder;

/**
 * test for the map builder.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class MapBuilderTest {
    
    @Test
    public void testNull() {
        Assert.assertNotNull(MapBuilder.createWith(String.class, String.class));
    }
    
    @Test
    public void test1() {
        Map<String, String> m = MapBuilder.createWith(String.class, String.class).put("a", "b").build();
        Assert.assertNotNull(m);
        Assert.assertEquals(m.size(), 1);
        Assert.assertEquals(m.get("a"), "b");
    }
}
