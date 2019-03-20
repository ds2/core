package ds2.oss.core.jee.jwt;

import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.IErrorData;

public class JwtContentException extends CoreException {
    public JwtContentException(JwtErrorCodes d, String msg) {
        super(d, msg);
    }

    public JwtContentException(JwtErrorCodes d, String msg, Throwable t) {
        super(d, msg, t);
    }
}
