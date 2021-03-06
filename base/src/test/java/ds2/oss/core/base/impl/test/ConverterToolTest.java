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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.base.impl.test;

import ds2.core.testonly.utils.AbstractInjectionEnvironment;
import ds2.oss.core.api.ConverterTool;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Tests for the converter.
 *
 * @author dstrauss
 * @version 0.3
 */
public class ConverterToolTest extends AbstractInjectionEnvironment {
    /**
     * The text object.
     */
    private ConverterTool to;

    @BeforeClass
    public void onClass() {
        to = getInstance(ConverterTool.class);
        Assert.assertNotNull(to);
    }

    @Test
    public void testToDate1() {
        long ms = 123456L;
        Date date = to.toDate(ms);
        Assert.assertNotNull(date);
        Assert.assertEquals(date.getTime(), ms);
    }

    @Test
    public void testToDateCal() {
        long ms = 123456L;
        Date date = to.toDate(ms);
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.US);
        cal.setTimeInMillis(ms);
        Date date2 = cal.getTime();
        Assert.assertNotNull(date);
        Assert.assertEquals(date.getTime(), ms);
        Assert.assertEquals(date, date2);
    }
}
