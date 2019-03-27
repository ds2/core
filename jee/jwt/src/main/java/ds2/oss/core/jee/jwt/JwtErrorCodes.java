package ds2.oss.core.jee.jwt;

import ds2.oss.core.api.IErrorData;

public enum JwtErrorCodes implements IErrorData {
    CODEC(1),
    INVALID_KEY(2),
    UNKNOWN_ALGORITHM(3);
    private int id;

    JwtErrorCodes(int i) {
        id = i;
    }

    @Override
    public int getId() {
        return id;
    }
}
