package ds2.oss.core.api.crypto;

/**
 * Attached to a class to indicate that we need a specific algorithm name from it.
 */
public interface AlgorithmNamed {
    /**
     * The name of the algorithm.
     * @return the name
     */
    String getAlgorithmName();
}
