package ds2.oss.core.api.crypto;

/**
 * Created by deindesign on 11.03.16.
 */
public enum KeyPairGenAlgorithm implements AlgorithmNamed{
    RSA("RSA");
    private String algorithmName;

    KeyPairGenAlgorithm(String a){
        algorithmName=a;
    }

    @Override
    public String getAlgorithmName() {
        return algorithmName;
    }
}
