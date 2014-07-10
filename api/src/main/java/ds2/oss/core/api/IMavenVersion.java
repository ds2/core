/**
 * 
 */
package ds2.oss.core.api;

import java.util.Date;

/**
 * Specialization of a version: a Maven version.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface IMavenVersion extends Version {
    boolean isSnapshot();
    
    Date getSnapshotDate();
}
