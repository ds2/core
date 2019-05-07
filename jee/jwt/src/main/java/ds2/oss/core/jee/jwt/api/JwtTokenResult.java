package ds2.oss.core.jee.jwt.api;

import ds2.oss.core.jee.jwt.TokenDataMap;
import ds2.oss.core.jee.jwt.dto.HeaderDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class JwtTokenResult {
    private HeaderDto joseHeader;
    private TokenDataMap dataMap;
}
