package ds2.oss.core.api.crypto;

public interface PasswordService {
    /**
     * A simple way to create a hash value out of a given password.
     *
     * @param pw the password
     * @return the hash value to persist.
     */
    String encryptPw(char[] pw) throws CoreCryptoException;

    boolean isValidPassword(String hash, char[] pw);
}
