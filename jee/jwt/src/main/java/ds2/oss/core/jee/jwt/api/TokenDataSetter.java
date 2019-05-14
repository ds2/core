package ds2.oss.core.jee.jwt.api;

import java.time.LocalDateTime;
import java.util.List;

public interface TokenDataSetter extends CommonJwtData {
    void setId(String id);

    void setIssuer(String issuer);

    void setCreated(LocalDateTime created);

    void setExpirationTime(LocalDateTime expirationTime);

    void setNotBefore(LocalDateTime notBefore);

    void setJwtId(String jwtId);

    void setSubject(String subject);

    void setAudience(List<String> audience);
}
