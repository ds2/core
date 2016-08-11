package ds2.oss.core.api;

import java.math.BigDecimal;

/**
 * Created by dstrauss on 08.08.16.
 */
public interface FinanceServices {
    /**
     * Calculates a netto value based on the given brutto value.
     *
     * @param val         the value
     * @param vat         usually 119 for a vat value of 19 percent
     * @param targetScale the target scale
     * @return the calculated price
     */
    BigDecimal calculateNettoPrice(BigDecimal val, long vat, int targetScale);
}
