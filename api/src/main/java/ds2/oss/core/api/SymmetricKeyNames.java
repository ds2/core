package ds2.oss.core.api;

/**
 * All known instance names for secret key factories.
 */
public enum SymmetricKeyNames {
    PBKDF2("PBKDF2WithHmacSHA1")
    ;
    private String name;
    private SymmetricKeyNames(String n){
        name=n;
    }
    public String getName(){
        return name;
    }
}
