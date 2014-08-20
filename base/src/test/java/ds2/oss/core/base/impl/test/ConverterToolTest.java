/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.base.impl.test;

import ds2.oss.core.api.ConverterTool;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author dstrauss
 */
public class ConverterToolTest extends WeldWrapper {

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
