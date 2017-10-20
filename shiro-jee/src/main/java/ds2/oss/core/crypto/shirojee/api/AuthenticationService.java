package ds2.oss.core.crypto.shirojee.api;

import java.util.List;

public interface AuthenticationService {
    boolean authenticateBasic(String username, char[] pw);

    List<String> getRolesOfUser(String username, char[] pw);

    boolean hasPermission(String permission);

    boolean isInRole(String role);

    /**
     * logs out the current subject on the thread.
     */
    void logout();
}
