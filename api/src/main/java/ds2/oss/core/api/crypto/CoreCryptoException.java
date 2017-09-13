package ds2.oss.core.api.crypto;

import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.IErrorData;

public class CoreCryptoException extends CoreException {
    public CoreCryptoException(IErrorData d, String msg) {
        super(d, msg);
    }

    public CoreCryptoException(IErrorData d, String msg, Throwable t) {
        super(d, msg, t);
    }
}
