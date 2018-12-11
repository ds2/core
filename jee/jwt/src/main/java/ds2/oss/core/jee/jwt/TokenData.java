package ds2.oss.core.jee.jwt;

import java.time.LocalDateTime;
import java.util.Map;

public interface TokenData extends Map<String, Object> {
    String FIELD_ID = "idt";

    String getId();

    LocalDateTime getCreated();

    LocalDateTime getValidUntil();
}
