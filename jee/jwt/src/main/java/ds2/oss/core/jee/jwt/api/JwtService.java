package ds2.oss.core.jee.jwt.api;

import java.util.Map;

public interface JwtService {
    String encode(Map<String,Object> content);
}
