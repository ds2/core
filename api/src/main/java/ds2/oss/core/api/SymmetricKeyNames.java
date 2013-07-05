package ds2.oss.core.api;

/**
 * All known instance names for secret key factories.
 * 
 * @version 0.2
 * @author dstrauss
 */
public enum SymmetricKeyNames {
    /**
     * The PBKDF2 algorithm.
     */
    PBKDF2("PBKDF2WithHmacSHA1", 160);
    /**
     * The hash name.
     */
    private String name;
    /**
     * The known key length that this algorithm may produce.
     */
    private int keyLength;
    
    /**
     * INits the enum value.
     * 
     * @param n
     *            the hash name
     * @param kl
     *            the key length
     */
    private SymmetricKeyNames(final String n, final int kl) {
        name = n;
        keyLength = kl;
    }
    
    /**
     * Returns the hash algorithm name.
     * 
     * @return the hash algorithm name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns the suggested key length.
     * 
     * @return the suggested key length
     */
    public int getSuggestedKeyLength() {
        return keyLength;
    }
}
