package ds2.oss.core.jee.jwt.api;

import lombok.Getter;

/**
 * See RFC7518.
 */
@Getter
public enum Algorithm {
    NONE("none", null),
    HMAC_SHA256("HS256", "HmacSHA256"),
    HS384("HS384", "HmacSHA384"),
    HS512("HS512", "HmacSHA512"),
    RS256("RS256", null),
    RS384("RS384", null),
    RS512("RS512", null),
    ES256("ES256", null),
    ES384("ES384", null),
    ES512("ES512", null),
    PS256("PS256", null),
    PS384("PS384", null),
    PS512("PS512", null),

    ;
    private String fieldValue;
    private String macName;

    Algorithm(String s, String m) {
        fieldValue = s;
        macName = m;
    }
}
