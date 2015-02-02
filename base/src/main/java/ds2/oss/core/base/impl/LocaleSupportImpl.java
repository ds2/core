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
package ds2.oss.core.base.impl;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.LocaleSupport;

/**
 * The impl.
 *
 * @author dstrauss
 * @version 0.3
 */
public class LocaleSupportImpl implements LocaleSupport {
    
    /**
     * The bundle controller to allow only a specific set of locales to search for.
     */
    private static final ResourceBundle.Control ctrl = new ResourceBundle.Control() {
        @Override
        public List<Locale> getCandidateLocales(final String baseName, final Locale locale) {
            LOG.debug("Calling candidates for base {} and locale {}", new Object[] { baseName, locale });
            if (baseName == null) {
                throw new IllegalArgumentException("No baseName given to load data from!");
            }
            if (locale == null) {
                LOG.warn("No locale given, using root locale!");
                return Arrays.asList(Locale.ROOT);
            }
            Locale langLocale = new Locale(locale.getLanguage());
            List<Locale> rc = Arrays.asList(locale, langLocale, Locale.ROOT);
            LOG.debug("Locales to return are {}", rc);
            return rc;
        }
        
        @Override
        public Locale getFallbackLocale(final String baseName, final Locale locale) {
            Locale rc = super.getFallbackLocale(baseName, locale);
            LOG.debug("Fallback locale is assumed to be {}", rc);
            rc = null;
            return rc;
        }
    };
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(LocaleSupportImpl.class);
    
    private static Locale parseLocale(final Locale locale) {
        if (locale != null) {
            return locale;
        }
        return Locale.US;
    }
    
    /**
     * The base name to load the resources from.
     */
    private final String baseName;
    
    public LocaleSupportImpl(final String bn) {
        baseName = bn;
    }
    
    @Override
    public String formatCurrency(final Locale locale, final double val) {
        Locale thisLocale = parseLocale(locale);
        NumberFormat nf = NumberFormat.getCurrencyInstance(thisLocale);
        return nf.format(val);
    }
    
    @Override
    public String formatCurrencyNumber(final Locale l, final double val) {
        Locale thisLocale = parseLocale(l);
        NumberFormat nf = NumberFormat.getCurrencyInstance(thisLocale);
        NumberFormat nf2 = NumberFormat.getNumberInstance(thisLocale);
        nf2.setMaximumFractionDigits(nf.getMaximumFractionDigits());
        nf2.setMinimumFractionDigits(nf.getMinimumFractionDigits());
        return nf2.format(val);
    }
    
    @Override
    public String formatDate(final Date date, final Locale locale, final TimeZone tz, final int dateStyle) {
        DateFormat df = DateFormat.getDateInstance(dateStyle, locale);
        if (tz != null) {
            df.setTimeZone(tz);
        }
        return df.format(date);
    }
    
    @Override
    public String formatDateTime(final Date date, final Locale locale, final TimeZone tz, final int dateStyle,
        final int timeStyle) {
        DateFormat df = DateFormat.getDateTimeInstance(dateStyle, timeStyle, locale);
        if (tz != null) {
            df.setTimeZone(tz);
        }
        return df.format(date);
    }
    
    @Override
    public String resolve(final String key, final Locale loc, final Object... params) {
        Locale thisLocale = parseLocale(loc);
        LOG.debug("Entering resolve with key {}, locale {} and params like {}",
            new Object[] { key, thisLocale, params });
        String rc = null;
        try {
            ResourceBundle rb = ResourceBundle.getBundle(baseName, thisLocale, getClass().getClassLoader(), ctrl);
            LOG.debug("bundle is {}", rb);
            rc = rb.getString(key);
            LOG.debug("Pattern found is {}", rc);
            if (params != null && params.length > 0) {
                MessageFormat mf2 = new MessageFormat(rc, thisLocale);
                rc = mf2.format(params);
            }
            LOG.debug("Returning value {}", rc);
            return rc;
        } catch (MissingResourceException e) {
            throw new IllegalStateException("Cannot find resource with base " + baseName, e);
        }
    }
    
    @Override
    public <E extends Enum<E>> String resolveEnum(final Locale locale, final E e) {
        String key = e.getClass().getSimpleName() + "." + e.name();
        return resolve(key, locale);
    }
    
    @Override
    public <E extends Enum<E>> String resolveException(final Locale locale, final E errorEnum, final Throwable t,
        final Object... params) {
        return null;
    }
}
