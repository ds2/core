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
package ds2.oss.core.crypto.test;

import ds2.core.testonly.utils.AbstractInjectionEnvironment;
import ds2.oss.core.api.HexCodec;
import ds2.oss.core.api.crypto.BytesProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;

/**
 * Simple test for the secure bytes provider.
 *
 * @author dstrauss
 * @version 0.3
 */
public class SecureBytesProviderTest extends AbstractInjectionEnvironment {
    /**
     * A logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    /**
     * The bytes provider.
     */
    private BytesProvider to;
    /**
     * The hex codec.
     */
    private HexCodec hex;

    @BeforeClass
    public void onClass() {
        to = getInstance(BytesProvider.class);
        hex = getInstance(HexCodec.class);
    }

    @Test
    public void testCreateNegativeOr0() {
        Assert.assertNull(to.createRandomByteArray(-1));
    }

    @Test
    public void testCreate0() {
        Assert.assertNull(to.createRandomByteArray(0));
    }

    @Test
    public void testCreate1() {
        byte[] bytes = to.createRandomByteArray(16);
        Assert.assertNotNull(bytes);
        Assert.assertTrue(bytes.length == 16);
        String encodedChars = hex.encode(bytes);
        LOG.info("Hex is {}", encodedChars);
    }

    @Test
    public void testCreate2() {
        byte[] bytes = to.createRandomByteArray(256);
        Assert.assertNotNull(bytes);
        Assert.assertTrue(bytes.length == 256);
        String encodedChars = hex.encode(bytes);
        LOG.info("Hex is {}", encodedChars);
    }
}
