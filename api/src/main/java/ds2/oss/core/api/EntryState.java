package ds2.oss.core.api;

import java.io.Serializable;

/**
 * Definition of a db entry state. Usually, you want to have something like ACTIVE, DELETED, LOCKED
 * etc. That is the contract of this interface: it assures that any implementation of this interface
 * has an id and a name.
 * 
 * @author dstrauss
 */
public interface EntryState extends NumericEnumValue, Serializable {
    /**
     * Returns the entry state name.
     * 
     * @return the name of the state
     */
    String getEntryStateName();
}
