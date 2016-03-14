package ds2.oss.core.api.crypto;

import javax.crypto.SecretKey;

/**
 * Created by deindesign on 11.03.16.
 */
public enum SecretKeyFactories {
    PBKDF2WithHmacSHA1("PBKDF2WithHmacSHA1");
    private String algorithmName;
    SecretKeyFactories(String s){
        algorithmName=s;
    }
    public String getAlgorithmName(){
        return algorithmName;
    }
}
