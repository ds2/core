package ds2.oss.core.crypto.shirojee.api;

public interface AuthenticationService {
    boolean authenticateBasic(String username, char[] pw);
}
