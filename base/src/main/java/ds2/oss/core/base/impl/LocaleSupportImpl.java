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

import ds2.oss.core.api.LocaleSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * The impl.
 *
 * @author dstrauss
 * @version 0.3
 */
public class LocaleSupportImpl implements LocaleSupport {

    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(LocaleSupportImpl.class);
    /**
     * The base name to load the resources from.
     */
    private final String baseName;
    /**
     * The bundle controller to allow only a specific set of locales to search
     * for.
     */
    private static final ResourceBundle.Control ctrl = new ResourceBundle.Control() {
        @Override
        public List<Locale> getCandidateLocales(String baseName, Locale locale) {
            LOG.debug("Calling candidates for base {} and locale {}", new Object[]{baseName, locale});
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
        public Locale getFallbackLocale(String baseName, Locale locale) {
            Locale rc = super.getFallbackLocale(baseName, locale);
            LOG.debug("Fallback locale is assumed to be {}", rc);
            rc = null;
            return rc;
        }
    };

    public LocaleSupportImpl(String bn) {
        this.baseName = bn;
    }

    private static Locale parseLocale(Locale locale) {
        if (locale != null) {
            return locale;
        }
        return Locale.US;
    }

    @Override
    public String resolve(String key, Locale loc, Object... params) {
        Locale thisLocale = parseLocale(loc);
        LOG.debug("Entering resolve with key {}, locale {} and params like {}", new Object[]{key, thisLocale, params});
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
    public String formatCurrency(Locale locale, double val) {
        Locale thisLocale = parseLocale(locale);
        NumberFormat nf = NumberFormat.getCurrencyInstance(thisLocale);
        return nf.format(val);
    }

    @Override
    public <E extends Enum<E>> String resolveException(Locale locale, E errorEnum, Throwable t, Object... params) {
        return null;
    }

    @Override
    public <E extends Enum<E>> String resolveEnum(Locale locale, E e) {
        String key = e.getClass().getSimpleName() + "." + e.name();
        return resolve(key, locale);
    }

    @Override
    public String formatDate(Date date, Locale locale, TimeZone tz, int dateStyle) {
        DateFormat df = DateFormat.getDateInstance(dateStyle, locale);
        if (tz != null) {
            df.setTimeZone(tz);
        }
        return df.format(date);
    }

    @Override
    public String formatDateTime(Date date, Locale locale, TimeZone tz, int dateStyle, int timeStyle) {
        DateFormat df = DateFormat.getDateTimeInstance(dateStyle, timeStyle, locale);
        if (tz != null) {
            df.setTimeZone(tz);
        }
        return df.format(date);
    }

    @Override
    public String formatCurrencyNumber(Locale l, double val) {
        Locale thisLocale = parseLocale(l);
        NumberFormat nf = NumberFormat.getCurrencyInstance(thisLocale);
        NumberFormat nf2 = NumberFormat.getNumberInstance(thisLocale);
        nf2.setMaximumFractionDigits(nf.getMaximumFractionDigits());
        nf2.setMinimumFractionDigits(nf.getMinimumFractionDigits());
        return nf2.format(val);
    }
}
