package ds2.oss.core.abstracts;

import ds2.oss.core.api.ValidationService;
import ds2.oss.core.statics.Methods;

/**
 * Basic implementation for the validation service.
 */
public abstract class AbstractValidationServiceImpl<E extends Exception> implements ValidationService<E> {

    @Override
    public <O> O checkNotNull(O obj, String errorMsg) throws E {
        if (obj == null) {
            throw throwException(errorMsg);
        }
        return obj;
    }

    @Override
    public String checkNotEmpty(String s, String errorMsg) throws E {
        if (Methods.isBlank(s)) {
            throw throwException(errorMsg);
        }
        return s;
    }

    protected abstract E throwException(String errorMsg) throws E;
}
