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
package ds2.oss.core.codec.jackson2;

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
public class JacksonJsonCodecTest extends AbstractInjectionEnvironment {

    private JsonCodec to;

    @BeforeClass
    public void onClass() {
        to = getInstance(JsonCodec.class);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEncodeNull() throws CoreException {
        to.encode(null);
    }

    @Test
    public void testEncode1() throws MalformedURLException, CoreException {
        Complex1 c = new Complex1();
        c.setCreated(new Date(12345L));
        c.setHomepage(new URL("http://www.bla.test"));
        c.setMsg("Hello, world");
        c.setNumber(123);
        c.setState(MyEnum.VAL2);
        String json = to.encode(c);
        Assert
                .assertEquals(
                        json,
                        "{\"msg\":\"Hello, world\",\"homepage\":\"http://www.bla.test\",\"created\":12345,\"childs\":null,\"num\":123,\"this_state_is\":\"VAL2\"}");
    }
}
