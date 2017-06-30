package ds2.oss.core.api.auth;

import java.util.Set;

/**
 * Created by dstrauss on 28.06.17.
 */
public interface Oauth2Credentials {
    String getAccessToken();

    String getRefreshToken();

    String getTokenType();

    int getExpiresInSeconds();

    Set<String> getScopes();
}
