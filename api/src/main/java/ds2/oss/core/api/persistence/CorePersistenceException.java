package ds2.oss.core.api.persistence;

import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.IErrorData;

public class CorePersistenceException extends CoreException {
    public CorePersistenceException(IErrorData d, String msg) {
        super(d, msg);
    }

    public CorePersistenceException(IErrorData d, String msg, Throwable t) {
        super(d, msg, t);
    }
}
