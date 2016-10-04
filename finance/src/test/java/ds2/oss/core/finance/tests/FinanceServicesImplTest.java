package ds2.oss.core.finance.tests;

import ds2.oss.core.api.FinanceServices;
import ds2.oss.core.finance.FinanceServicesImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;

/**
 * Created by dstrauss on 04.10.16.
 */
public class FinanceServicesImplTest {
    private FinanceServices to;

    @BeforeClass
    public void onClass() {
        to = new FinanceServicesImpl();
    }

    @Test
    public void testVat1() {
        BigDecimal rc = to.calculateNettoPrice(BigDecimal.valueOf(1995, 2), BigDecimal.valueOf(19, 0), 2);
        Assert.assertEquals(rc, BigDecimal.valueOf(1676, 2));
    }
}
