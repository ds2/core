package ds2.oss.core.api.crypto;

/**
 * Sample keypair algorithm names.
 * @author dstrauss
 */
public enum KeyPairGenAlgorithm implements AlgorithmNamed{
    /**
     * Default algorithm. Use this if unsure.
     */
    RSA("RSA"),
    /**
     * Elliptic curve.
     */
    EC("EC"), DH("DiffieHellman"), DSA("DSA");
    private String algorithmName;

    KeyPairGenAlgorithm(String a){
        algorithmName=a;
    }

    @Override
    public String getAlgorithmName() {
        return algorithmName;
    }
}
