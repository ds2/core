/**
 * 
 */
package ds2.oss.core.api;

/**
 * Info about a specific version of an operating system.
 * @author dstrauss
 * @version 0.3
 *
 */
public interface OperatingSystemVersion extends Comparable<OperatingSystemVersion> {
	/**
	 * Returns the system name.
	 * @return the system name
	 */
	OperatingSystemNames getSystemName();
	/**
	 * Returns the version number.
	 * @return the version number
	 */
	Version getVersion();
}
