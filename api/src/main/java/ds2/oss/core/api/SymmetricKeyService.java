package ds2.oss.core.api;

/**
 * Contract for a symmetric key hashing service.
 * @author dstrauss
 * @version 0.2
 */
public interface SymmetricKeyService {
    /**
     * Creates a hash based on the given origin data and the algorithm to use.
     * @param origin the origin data to hash
     * @param n the algorithm to use
     * @return the result hash, or null if an error occurred
     */
    byte[] performHashing(char[] origin, SymmetricKeyNames n);
    byte[] performHashing(char[] origin, byte[] salt, int iterationCount, SymmetricKeyNames n);
}
