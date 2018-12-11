package ds2.oss.core.jee.jwt;

import java.time.LocalDateTime;
import java.util.HashMap;

public class TokenDataMap extends HashMap<String,Object> implements TokenData {
    @Override
    public String getId() {
        return null;
    }

    @Override
    public LocalDateTime getCreated() {
        return null;
    }

    @Override
    public LocalDateTime getValidUntil() {
        return null;
    }
}
