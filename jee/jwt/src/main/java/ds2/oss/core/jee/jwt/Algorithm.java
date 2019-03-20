package ds2.oss.core.jee.jwt;

import lombok.Getter;

@Getter
public enum Algorithm {
    NONE("none");
    private String fieldValue;

    Algorithm(String s) {
        fieldValue = s;
    }
}
