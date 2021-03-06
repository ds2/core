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
/**
 *
 */
package ds2.oss.core.infinispan.tests;

import ds2.core.testonly.utils.AbstractInjectionEnvironment;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 * @author dstrauss
 *
 */
@Test
public class InfinispanTest extends AbstractInjectionEnvironment {

    private MyOptionStoreServiceImpl to;

    @BeforeClass
    public void onLoad() {
        to = getInstance(MyOptionStoreServiceImpl.class);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNull() {
        to.store(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testOptionOhneKey() {
        to.store(new MyOption());
    }

    @Test
    public void testOptionValid() {
        MyOption option = new MyOption();
        option.setKey("k1");
        option.setVal("val");
        to.store(option);
    }

    @Test(dependsOnMethods = "testOptionValid")
    public void testGetOption() {
        MyOption option = to.get("k1");
        Assert.assertNotNull(option);
    }
}
