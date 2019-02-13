/*
 * Copyright 2019 DS/2 <dstrauss@ds-2.de>
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
package ds2.oss.core.elasticsearch.impl;

import ds2.oss.core.statics.Methods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A small code base to support date formatting.
 *
 * @author dstrauss
 * @version 0.2
 */
public abstract class AbstractCodecBase {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(AbstractCodecBase.class);
    /**
     * The formatter.
     */
    private SimpleDateFormat sdf;
    private static final Lock LOCK=new ReentrantLock();

    /**
     * Inits the base.
     */
    protected AbstractCodecBase() {
        sdf = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss.SSS\'Z\'", Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    /**
     * Parses a date into a string.
     *
     * @param d the date to format
     * @return the string
     */
    public String fromDate(final Date d) {
        LOCK.lock();
        try {
            if (d == null) {
                return null;
            }
            String rc = null;
            rc = sdf.format(d);
            return rc;
        } finally {
            LOCK.unlock();
        }
    }

    /**
     * Parses a given string into a date object.
     *
     * @param esDate the date string
     * @return the parsed date
     */
    public Date toDate(final String esDate) {
        LOCK.lock();
        try {
            LOG.debug("Trying to convert to date: {}", esDate);
            Date rc = null;
            if (!Methods.isBlank(esDate)) {
                LOG.debug("Parsing date {}", esDate);
                try {
                    rc = sdf.parse(esDate);
                } catch (final ParseException e) {
                    LOG.warn("Error when parsing given date!", e);
                }
            }
            LOG.debug("Returning date: {}", rc);
            return rc;
        } finally {
            LOCK.unlock();
        }
    }

}
