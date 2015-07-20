package ds2.oss.core.api;

import java.io.Serializable;

/**
 * Definition of a db entry state.
 */
public interface EntryState extends NumericEnumValue, Serializable {
    /**
     * Returns the entry state name.
     * 
     * @return the name of the state
     */
    String getEntryStateName();
}
