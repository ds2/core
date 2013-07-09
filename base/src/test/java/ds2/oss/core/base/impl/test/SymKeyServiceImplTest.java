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

import java.io.UnsupportedEncodingException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ds2.oss.core.api.HexCodec;
import ds2.oss.core.api.SymmetricKeyNames;
import ds2.oss.core.api.SymmetricKeyService;

/**
 * Tests.
 * 
 * @version 0.2
 * @author dstrauss
 */
@Test(groups = "sym")
public class SymKeyServiceImplTest extends WeldWrapper {
    /**
     * The test object.
     */
    private SymmetricKeyService to;
    /**
     * The hex codec.
     */
    private HexCodec hx;
    
    @BeforeClass
    public void onClass() {
        to = getInstance(SymmetricKeyService.class);
        hx = getInstance(HexCodec.class);
    }
    
    @Test
    public void testInject() {
        Assert.assertNotNull(to);
    }
    
    @Test
    public void rfc6080_1() throws UnsupportedEncodingException {
        byte[] b =
            to.performHashing("password".toCharArray(),
                "salt".getBytes("utf-8"), 1, SymmetricKeyNames.PBKDF2);
        String erg = hx.encode(b);
        Assert.assertEquals(erg, "0c60c80f961f0e71f3a9b524af6012062fe037a6");
    }
    
    @Test
    public void rfc6080_2() throws UnsupportedEncodingException {
        byte[] b =
            to.performHashing("password".toCharArray(),
                "salt".getBytes("utf-8"), 2, SymmetricKeyNames.PBKDF2);
        String erg = hx.encode(b);
        Assert.assertEquals(erg, "ea6c014dc72d6f8ccd1ed92ace1d41f0d8de8957");
    }
    
    @Test
    public void rfc6080_3() throws UnsupportedEncodingException {
        byte[] b =
            to.performHashing("pass\0word".toCharArray(),
                "sa\0lt".getBytes("utf-8"), 4096, SymmetricKeyNames.PBKDF2);
        String erg = hx.encode(b);
        Assert.assertEquals(erg, "56fa6aa75548099dcc37d7f03425e0c37f1c42b2");
    }
}
