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
package ds2.oss.core.codec.boon;

import ds2.core.testonly.utils.AbstractInjectionEnvironment;
import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.JsonCodec;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * @author dstrauss
 */
public class BoonJsonCodecTest extends AbstractInjectionEnvironment {

    private JsonCodec to;

    @BeforeClass
    public void onClass() {
        to = getInstance(JsonCodec.class);
    }

    @Test
    public void testEncodeNull() throws CoreException {
        Assert.assertNull(to.encode(null));
    }

    @Test
    public void testEncode1() throws CoreException, MalformedURLException {
        Complex1 c = new Complex1();
        c.setCreated(new Date(123L));
        c.setHomepage(new URL("http://www.bla.test"));
        c.setMsg("Hello, World");
        c.setNumber(23);
        c.setState(MyEnum.VAL1);
        Assert
                .assertEquals(to.encode(c),
                        "{\"number\":23,\"msg\":\"Hello, World\",\"homepage\":\"http://www.bla.test\",\"created\":123,\"state\":\"VAL1\"}");
    }
}
