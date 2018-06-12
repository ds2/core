package ds2.oss.core.api.crypto;

/**
 * Created by deindesign on 11.03.16.
 */
public enum SecretKeyFactories {
    PBKDF2WithHmacSHA1("PBKDF2WithHmacSHA1"),
    SHA512withRSA("SHA512withRSA"),
    HmacSHA512("HmacSHA512");
    private String algorithmName;

    SecretKeyFactories(String s) {
        algorithmName = s;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }
}
