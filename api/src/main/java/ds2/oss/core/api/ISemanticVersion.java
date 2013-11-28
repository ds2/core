/**
 * 
 */
package ds2.oss.core.api;

/**
 * Specialization of a version: the semantic version.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface ISemanticVersion extends Version {
    /**
     * Increments the major version, setting minor and patch to 0.
     */
    void incrementMajorNumber();
    
    /**
     * Increments the minor number, setting the patch number to 0.
     */
    void incrementMinorNumber();
    
    /**
     * Increments the patch number.
     */
    void incrementPatchNumber();
}
