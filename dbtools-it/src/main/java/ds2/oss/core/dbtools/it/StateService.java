/**
 * 
 */
package ds2.oss.core.dbtools.it;

import ds2.oss.core.api.EntryState;

/**
 * @author dstrauss
 *
 */
public interface StateService {
    EntryState createStateByName(String s);
}
