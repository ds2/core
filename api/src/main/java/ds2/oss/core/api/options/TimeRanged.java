package ds2.oss.core.api.options;

import java.util.Date;

/**
 * A contract for a time ranged value.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface TimeRanged {
    /**
     * Returns the start date of the value.
     * 
     * @return the start date
     */
    Date validFrom();
    
    /**
     * Returns the stop date of the value.
     * 
     * @return the stop date
     */
    Date validTo();
}
