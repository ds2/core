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

import ds2.core.testonly.utils.AbstractInjectionEnvironment;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


/**
 * The test for the LocaleSupport.
 *
 * @author dstrauss
 * @version 0.3
 */
@Test(groups = "localeSupport")
public class LocaleSupportImplTest extends AbstractInjectionEnvironment {

    /**
     * The provider that contains the injection.
     */
    private LocaleSupportTestProvider to;

    @BeforeClass
    public void onClass() {
        to = getInstance(LocaleSupportTestProvider.class);
    }

    @Test
    public void testHelloNull() {
        Assert.assertEquals(to.getHello(null), "Hello");
    }

    @Test
    public void testHelloGerman() {
        Assert.assertEquals(to.getHello(Locale.GERMANY), "Hallo");
    }

    @Test
    public void testHelloGermanParams() {
        Assert.assertEquals(to.getHelloParam(Locale.GERMANY, "Dirk"), "Hallo, Dirk");
    }

    @Test
    public void testHelloParams() {
        Assert.assertEquals(to.getHelloParam(Locale.US, "Dirk"), "Hello, Dirk");
    }

    @Test
    public void testCurrencyConvertNull() {
        Assert.assertEquals(to.formatCurrency(null, 0), "$0.00");
    }

    @Test
    public void testCurrencyConvert1() {
        Assert.assertEquals(to.formatCurrency(Locale.GERMANY, 1234.56), "1.234,56 \u20ac");
    }

    @Test
    public void testCurrencyConvert2() {
        Assert.assertEquals(to.formatCurrency(Locale.US, 1234.56), "$1,234.56");
    }

    @Test
    public void testCurrencyNumberConvert1() {
        Assert.assertEquals(to.formatCurrencyNumber(Locale.US, 1.23456), "1.23");
    }

    @Test
    public void testCurrencyNumberConvert2() {
        Assert.assertEquals(to.formatCurrencyNumber(Locale.US, 123456789.0123456), "123,456,789.01");
    }

    @Test
    public void testFormatDate1() {
        Date date = new Date(123456789);
        Assert.assertEquals(to.formatDate(date, Locale.US, TimeZone.getTimeZone("PST"), DateFormat.SHORT), "1/2/70");
    }

    @Test
    public void testFormatDate2() {
        Date date = new Date(123456789);
        Assert.assertEquals(to.formatDate(date, Locale.US, TimeZone.getTimeZone("PST"), DateFormat.FULL),
                "Friday, January 2, 1970");
    }

    @Test
    public void testFormatDateTime1() {
        Date date = new Date(123456789);
        Assert.assertEquals(
                to.formatDateTime(date, Locale.US, TimeZone.getTimeZone("PST"), DateFormat.SHORT, DateFormat.SHORT),
                "1/2/70 2:17 AM");
    }

    @Test
    public void testFormatDateTime2() {
        Date date = new Date(123456789);
        Assert.assertEquals(
                to.formatDateTime(date, Locale.US, TimeZone.getTimeZone("PST"), DateFormat.FULL, DateFormat.FULL),
                "Friday, January 2, 1970 2:17:36 AM PST");
    }

    @Test
    public void testFormatDateTime3() {
        Date date = new Date(123456789);
        Assert.assertEquals(
                to.formatDateTime(date, Locale.GERMANY, TimeZone.getTimeZone("CET"), DateFormat.FULL, DateFormat.FULL),
                "Freitag, 2. Januar 1970 11:17 Uhr MEZ");
    }
}
