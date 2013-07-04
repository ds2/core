package ds2.oss.core.api;

/**
 * All known instance names for secret key factories.
 */
public enum SymmetricKeyNames {
    PBKDF2("PBKDF2WithHmacSHA1",160)
    ;
    private String name;
    private int keyLength;
    private SymmetricKeyNames(String n, int kl){
        name=n;
        keyLength=kl;
    }
    public String getName(){
        return name;
    }
    public int getSuggestedKeyLength(){
        return keyLength;
    }
}
