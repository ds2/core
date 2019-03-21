package ds2.oss.core.jee.jwt;

import lombok.Getter;

@Getter
public enum Algorithm {
    NONE("none"),
    HMAC_SHA256("HS256");
    private String fieldValue;

    Algorithm(String s) {
        fieldValue = s;
    }
}
