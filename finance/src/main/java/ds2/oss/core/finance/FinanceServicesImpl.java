/*
 * Copyright 2018 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
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
    public BigDecimal calculateNettoPrice(BigDecimal val, BigDecimal vat, int targetScale) {
        LOG.debug("MA value to calculate is {}", val);
        val = val.setScale(targetScale, RoundingMode.HALF_UP);
        BigDecimal rc = val;
        rc = rc.multiply(BigDecimal.valueOf(100l));
        if (vat.compareTo(BigDecimal.valueOf(100L)) < 0) {
            vat = vat.add(BigDecimal.valueOf(100));
        }
        rc = rc.divide(vat, targetScale, RoundingMode.HALF_UP);
        LOG.debug("rc from netto price is {}", rc);
        return rc;
    }
}
