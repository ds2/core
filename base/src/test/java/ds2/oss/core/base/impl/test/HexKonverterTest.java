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

import java.io.UnsupportedEncodingException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ds2.oss.core.api.HexCodec;

/**
 * Testcase for the hex converter.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class HexKonverterTest extends WeldWrapper {
    /**
     * The contract to use.
     */
    private HexCodec to;
    
    /**
     * Decoder test.
     * 
     * @throws UnsupportedEncodingException
     *             if an error occurred.
     */
    @Test
    public final void decode1() throws UnsupportedEncodingException {
        final String s = "74c3a47374";
        final byte[] b = to.decode(s.toCharArray());
        Assert.assertNotNull(b);
        final String erg = new String(b, "utf-8");
        Assert.assertEquals(erg, "t\u00e4st");
    }
    
    @Test
    public void decode2() throws UnsupportedEncodingException {
        final String s = "F1409DAD84000180";
        final byte[] b = to.decode(s.toCharArray());
        Assert.assertNotNull(b);
        Assert.assertEquals(b.length, 8);
    }
    
    @Test
    public void decodeWithHeader() throws UnsupportedEncodingException {
        final String s = "0xF1409DAD84000180";
        final byte[] b = to.decode(s.toCharArray());
        Assert.assertNotNull(b);
        Assert.assertEquals(b.length, 8);
    }
    
    @Test
    public void decodeNull() {
        Assert.assertNull(to.decode(null));
    }
    
    @Test
    public void encode1() throws UnsupportedEncodingException {
        final String s = "t\u00e4st";
        final String erg = to.encode(s.getBytes("utf-8"));
        Assert.assertNotNull(erg);
        Assert.assertEquals(erg, "74c3a47374");
    }
    
    @Test
    public void encodeNull() {
        Assert.assertNull(to.encode(null));
    }
    
    @BeforeClass
    public void onInit() {
        to = getInstance(HexCodec.class);
    }
}
