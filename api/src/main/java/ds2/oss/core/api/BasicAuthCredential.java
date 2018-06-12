package ds2.oss.core.api;

/**
 * Created by dstrauss on 23.03.17.
 */
public interface BasicAuthCredential extends Credential {
    String getUsername();

    char[] getPassword();
}
