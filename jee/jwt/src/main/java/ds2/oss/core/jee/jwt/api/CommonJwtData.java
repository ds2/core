package ds2.oss.core.jee.jwt.api;

import java.time.LocalDateTime;
import java.util.List;

public interface CommonJwtData {
    String CLAIM_ID = "idt";
    String CLAIM_ISSUER = "iss";
    String CLAIM_SUBJECT = "sub";
    String CLAIM_AUDIENCE = "aud";
    String CLAIM_EXPIRATION_TIME = "exp";
    String CLAIM_NOT_BEFORE = "nbf";
    String CLAIM_ISSUED_AT = "iat";
    String CLAIM_JWT_ID = "jti";
    String CLAIM_JOSE_TYPE = "typ";
    String CLAIM_JOSE_CONTENTTYPE = "cty";

    String getId();

    String getIssuer();

    LocalDateTime getCreated();

    LocalDateTime getExpirationTime();

    LocalDateTime getNotBefore();

    String getJwtId();

    String getSubject();

    List<String> getAudience();
}
