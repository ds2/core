/*
 * Copyright 2012-2013 Dirk Strauss
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
package ds2.oss.core.elasticsearch.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
    
    /**
     * Inits the base.
     */
    protected AbstractCodecBase() {
        sdf = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss.SSS\'Z\'", Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
    }
    
    /**
     * Parses a given string into a date object.
     * 
     * @param esDate
     *            the date string
     * @return the parsed date
     */
    public Date toDate(final String esDate) {
        Date rc = null;
        if (esDate == null) {
            return rc;
        }
        LOG.debug("Parsing date {}", esDate);
        try {
            rc = sdf.parse(esDate);
        } catch (final ParseException e) {
            LOG.warn("Error when parsing given date!", e);
        }
        return rc;
    }
    
    /**
     * Parses a date into a string.
     * 
     * @param d
     *            the date to format
     * @return the string
     */
    public String fromDate(final Date d) {
        if (d == null) {
            return null;
        }
        String rc = null;
        rc = sdf.format(d);
        return rc;
    }
    
    protected static String getAsString(final JsonObject obj, final String fieldName) {
        JsonElement s = obj.get(fieldName);
        if ((s == null) || s.isJsonNull()) {
            return null;
        }
        return s.getAsString();
    }
    
    protected static void addIfNotNull(final JsonObject obj, final String prop, final String val) {
        if (val == null) {
            return;
        }
        obj.addProperty(prop, val);
    }
}
