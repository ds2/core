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
package ds2.oss.core.elasticsearch.test;

import ds2.core.testonly.utils.AbstractInjectionEnvironment;
import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.JsonCodec;
import ds2.oss.core.elasticsearch.test.dto.MyNews;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * The gson codec tests.
 *
 * @author dstrauss
 * @version 0.2
 */
public class GsonCodecTest extends AbstractInjectionEnvironment {
    /**
     * The test object.
     */
    private JsonCodec to;

    @BeforeMethod(alwaysRun = true)
    public void onMethod() {
        to = getInstance(JsonCodec.class);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void serializeNull() throws CoreException {
        to.encode(null);
    }

    /**
     * Serializer test.
     *
     * @throws CoreException
     */
    @Test
    public void testSerializeNews() throws CoreException {
        final MyNews n = new MyNews();
        n.setAuthor("testuser");
        n.setMsg("Hello, world");
        n.setTitle("My Title");
        final Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.GERMANY);
        cal.set(Calendar.YEAR, 2013);
        cal.set(Calendar.MONTH, Calendar.JULY);
        cal.set(Calendar.DAY_OF_MONTH, 13);
        cal.set(Calendar.HOUR_OF_DAY, 19);
        cal.set(Calendar.MINUTE, 26);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        n.setPostDate(cal.getTime());
        final String s = to.encode(n);
        Assert
                .assertEquals(
                        s,
                        "{\"title\":\"My Title\",\"author\":\"testuser\",\"message\":\"Hello, world\",\"postDate\":\"2013-07-13T19:26:00.000Z\"}");
    }

    /**
     * Unserialize test.
     *
     * @throws CoreException
     */
    @Test
    public void testUnserialize() throws CoreException {
        final MyNews n =
                to.decode(
                        "{\"title\":\"My Title\",\"author\":\"testuser\",\"message\":\"Hello, world\",\"postDate\":\"2013-07-13T21:26:00.000+0200\"}",
                        MyNews.class);
        Assert.assertNotNull(n);
        Assert.assertEquals(n.getAuthor(), "testuser");
        Assert.assertEquals(n.getMsg(), "Hello, world");
    }
}
