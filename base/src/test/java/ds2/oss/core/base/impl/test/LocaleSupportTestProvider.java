/*
 * Copyright 2012-2014 Dirk Strauss
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

import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import ds2.oss.core.api.LocaleSupport;
import ds2.oss.core.api.annotations.LocaleData;
import java.util.TimeZone;

/**
 * A dummy provider.
 *
 * @version 0.3
 * @author dstrauss
 */
public class LocaleSupportTestProvider {

    /**
     * The injection.
     */
    @Inject
    @LocaleData(baseName = "ds2/oss/core/base/impl/test/LocTest")
    private LocaleSupport localeSupport;

    /**
     * The hello method.
     *
     * @param locale the locale
     * @return the resolved i18n string.
     */
    public String getHello(final Locale locale) {
        return localeSupport.resolve("hello", locale);
    }

    public String getHelloParam(final Locale locale, final String name) {
        return localeSupport.resolve("helloParam", locale, name);
    }

    public String formatCurrency(final Locale locale, final double val) {
        return localeSupport.formatCurrency(locale, val);
    }

    public String formatCurrencyNumber(final Locale locale, final double val) {
        return localeSupport.formatCurrencyNumber(locale, val);
    }

    public String formatDate(final Date date, final Locale locale, TimeZone tz, final int dateStyle) {
        return localeSupport.formatDate(date, locale, tz, dateStyle);
    }

    public String formatDateTime(final Date date, final Locale locale, TimeZone tz, final int dateStyle, final int timeStyle) {
        return localeSupport.formatDateTime(date, locale, tz, dateStyle, timeStyle);
    }
}
