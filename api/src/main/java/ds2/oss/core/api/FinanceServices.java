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
package ds2.oss.core.api;

import java.math.BigDecimal;

/**
 * Created by dstrauss on 08.08.16.
 */
public interface FinanceServices {
    /**
     * Calculates a netto value based on the given brutto value.
     *
     * @param val         the brutto value
     * @param vat         usually 119 for a vat value of 19 percent
     * @param targetScale the target scale
     * @return the calculated price
     */
    BigDecimal calculateNettoPrice(BigDecimal val, BigDecimal vat, int targetScale);
}
