package ds2.oss.core.api;

import java.io.Serializable;

/**
 * Created by dstrauss on 18.06.15.
 */
public interface EntryState extends NumericEnumValue, Serializable {
    String getEntryStateName();
}
