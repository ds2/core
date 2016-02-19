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
package ds2.oss.core.statics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author dstrauss
 *         
 */
public final class Convert {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    /**
     * Inits this object.
     */
    private Convert() {
        // nothing special to do
    }
    
    /**
     * Converts an object into an int.
     * 
     * @param o
     *            the object
     * @param defValue
     *            a default value
     * @return the converted value, or the default value
     */
    public static int toInt(Object o, int defValue) {
        int rc = defValue;
        if (o != null) {
            if (o instanceof Number) {
                final Number number = (Number) o;
                rc = number.intValue();
            } else if (o instanceof String) {
                try {
                    rc = Integer.parseInt(o.toString());
                } catch (NumberFormatException e) {
                    LOG.debug("Error when converting {} to int!",o, e);
                }
            }
        }
        return rc;
    }
    
    /**
     * Converts a given object into long.
     * 
     * @param o
     *            the object
     * @param defValue
     *            the default value
     * @return the long value
     */
    public static long toLong(Object o, long defValue) {
        long rc = defValue;
        if (o != null) {
            if (o instanceof Number) {
                final Number number = (Number) o;
                rc = number.longValue();
            } else if (o instanceof String) {
                try {
                    rc = Long.parseLong(o.toString());
                } catch (NumberFormatException e) {
                    LOG.debug("Error when converting {} to long!",o, e);
                }
            }
        }
        return rc;
    }

    /**
     * Converts a given string into a url.
     *
     * @param urlStr
     *            the url string
     * @return the url object, or null if an error occurred
     */
    public static URL toUrl(final String urlStr) {
        URL rc = null;
        try {
            rc = new URL(urlStr);
        } catch (final MalformedURLException e) {
            LOG.debug("Error when converting the given string into a url!", e);
        }
        return rc;
    }
    
}
