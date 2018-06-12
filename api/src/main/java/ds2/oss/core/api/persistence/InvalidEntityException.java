package ds2.oss.core.api.persistence;

import javax.persistence.PersistenceException;

public class InvalidEntityException extends PersistenceException {
    public InvalidEntityException(String message) {
        super(message);
    }

    public InvalidEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
