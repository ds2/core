/**
 * 
 */
package ds2.oss.core.api;

/**
 * Any hardware device.
 * @author dstrauss
 * @version 0.3
 */
public interface Device {
	/**
	 * Returns the vendor of the device.
	 * @return the device's vendor
	 */
	String getVendor();
	/**
	 * Returns the model name of this device.
	 * @return the model name
	 */
	String getModel();
}
