/**
 * 
 */
package ds2.oss.core.elasticsearch.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
}
