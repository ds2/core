/**
 * 
 */
package ds2.oss.core.statics;

import java.lang.invoke.MethodHandles;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author dstrauss
 *         
 */
public class Convert {
    /**
     * A logger.
     */
    private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
    
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
                    LOG.log(Level.FINE, "Error when converting " + o + " to int!", e);
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
                    LOG.log(Level.FINE, "Error when converting " + o + " to long!", e);
                }
            }
        }
        return rc;
    }
    
}
