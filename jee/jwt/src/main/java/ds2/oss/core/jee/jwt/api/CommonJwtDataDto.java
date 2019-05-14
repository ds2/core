package ds2.oss.core.jee.jwt.api;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CommonJwtDataDto implements TokenDataSetter {
    private String id;
    private String issuer;
    private LocalDateTime created;
    private LocalDateTime expirationTime;
    private LocalDateTime notBefore;
    private String jwtId;
    private String subject;
    private List<String> audience;
}
