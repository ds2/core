package ds2.oss.core.api.persistence;

import ds2.oss.core.api.IErrorData;

public class EntityNotFoundException extends CorePersistenceException {
    public EntityNotFoundException(IErrorData d, String msg) {
        super(d, msg);
    }

    public EntityNotFoundException(IErrorData d, String msg, Throwable t) {
        super(d, msg, t);
    }
}
