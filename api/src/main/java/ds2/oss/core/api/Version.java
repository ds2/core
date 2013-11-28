package ds2.oss.core.api;

import java.io.Serializable;

/**
 * A version.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface Version extends Serializable, Comparable<Version> {
    /**
     * Returns the major part of this version.
     * 
     * @return the major number part
     */
    int getMajorNumber();
    
    /**
     * Returns the minor part of this version.
     * 
     * @return the minor number part
     */
    int getMinorNumber();
    
    /**
     * Returns the patch part of this version.
     * 
     * @return the patch number part
     */
    int getPatchNumber();
}
