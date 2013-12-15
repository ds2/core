/**
 * 
 */
package ds2.oss.core.base.impl;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Some direct help methods, ready to use.
 * 
 * @author dstrauss
 * @version 0.3
 */
public final class Tools {
    /**
     * A logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    /**
     * May create an instance of the class.
     */
    private Tools() {
        // nothing special to do
    }
    
    /**
     * Converts a given object to long.
     * 
     * @param o
     *            the object
     * @param def
     *            the default value, of object is null
     * @return the default value, or the converted value
     */
    public static long toLong(final Object o, final long def) {
        long rc = def;
        if (o != null) {
            try {
                rc = Long.parseLong(o.toString());
            } catch (final NumberFormatException e) {
                LOG.debug("Error when converting the given object to long!", e);
            }
        }
        return rc;
    }
    
    public static int toInt(final Object o, final int def) {
        int rc = def;
        if (o != null) {
            try {
                rc = Integer.parseInt(o.toString());
            } catch (final NumberFormatException e) {
                LOG.debug("Error when converting the given object to int!", e);
            }
        }
        return rc;
    }
}
