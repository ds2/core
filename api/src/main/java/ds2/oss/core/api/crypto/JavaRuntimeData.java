/**
 * 
 */
package ds2.oss.core.api.crypto;

/**
 * Some details regarding the current java runtime.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface JavaRuntimeData {
    /**
     * Returns the recommended AES keysize of the java runtime. Default is 128 but depending on your
     * runtime, it may also be 256.
     * 
     * @return the AES keysize to use when generating keys
     */
    int getAesMaxKeysize();
}
