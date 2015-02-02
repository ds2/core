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
package ds2.oss.core.api;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * THe locale support.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface LocaleSupport {
    
    /**
     * Resolves a given property.
     * 
     * @param key
     *            the key to the property
     * @param loc
     *            the locale to use
     * @param params
     *            a list of possible params
     * @return the i18n string to use
     */
    String resolve(String key, Locale loc, Object... params);
    
    /**
     * Formats a given value into a currency string.
     * 
     * @param locale
     *            the locale to use
     * @param val
     *            the value to render
     * @return the formatted currency value
     */
    String formatCurrency(Locale locale, double val);
    
    /**
     * Formats only a given number into a currency specific number.
     * 
     * @param l
     *            the locale to use
     * @param val
     *            the value to render
     * @return a formatted value of the given number, matching the currency formatter rules
     */
    String formatCurrencyNumber(Locale l, double val);
    
    /**
     * Formats an exception.
     * 
     * @param <E>
     *            the exception enum
     * @param locale
     *            the target locale
     * @param errorEnum
     *            the error enum
     * @param t
     *            the exception
     * @param params
     *            a list of possible params
     * @return the error message to use
     */
    <E extends Enum<E>> String resolveException(Locale locale, E errorEnum, Throwable t, Object... params);
    
    /**
     * Formats an enum value.
     * 
     * @param <E>
     *            the enum type
     * @param locale
     *            the target locale
     * @param e
     *            the enum value
     * @return the string to use
     */
    <E extends Enum<E>> String resolveEnum(Locale locale, E e);
    
    /**
     * Formats a given date.
     * 
     * @param date
     *            the date
     * @param locale
     *            the locale
     * @param tz
     *            the time zone
     * @param dateStyle
     *            the date style
     * @return the rendered date
     */
    String formatDate(Date date, Locale locale, TimeZone tz, int dateStyle);
    
    /**
     * Formats a given date.
     * 
     * @param date
     *            the date
     * @param locale
     *            the locale
     * @param tz
     *            the time zone
     * @param dateStyle
     *            the date style
     * @param timeStyle
     *            the time style
     * @return the rendered date
     */
    String formatDateTime(Date date, Locale locale, TimeZone tz, int dateStyle, int timeStyle);
}
