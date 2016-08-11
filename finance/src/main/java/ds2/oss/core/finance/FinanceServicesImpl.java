package ds2.oss.core.finance;

import ds2.oss.core.api.FinanceServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by dstrauss on 08.08.16.
 */
public class FinanceServicesImpl implements FinanceServices {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public BigDecimal calculateNettoPrice(BigDecimal val, long vat, int targetScale) {
        LOG.debug("MA value to calculate is {}", val);
        val = val.setScale(targetScale, RoundingMode.HALF_UP);
        BigDecimal rc = val;
        rc = rc.multiply(BigDecimal.valueOf(100l));
        rc = rc.divide(BigDecimal.valueOf(vat), targetScale, RoundingMode.HALF_UP);
        LOG.debug("rc from netto price is {}", rc);
        return rc;
    }
}
