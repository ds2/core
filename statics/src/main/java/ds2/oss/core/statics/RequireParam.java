package ds2.oss.core.statics;

import ds2.oss.core.api.CoreErrors;
import ds2.oss.core.api.CoreException;

/**
 * Created by dstrauss on 27.03.17.
 */
public interface RequireParam {
    static <E> E requireNotNull(E e, String errorMsg) throws CoreException {
        if (e == null) {
            throw new CoreException(CoreErrors.IllegalArgument, errorMsg);
        }
        return e;
    }

    static String requireNotBlank(String e, String errorMsg) throws CoreException {
        requireNotNull(e, errorMsg);
        if (Methods.isBlank(e)) {
            throw new CoreException(CoreErrors.IllegalArgument, errorMsg);
        }
        return e;
    }

    static void requireTrue(Boolean b, String errorMsg) throws CoreException {
        requireNotNull(b, errorMsg);
        if (!b) {
            throw new CoreException(CoreErrors.IllegalArgument, errorMsg);
        }
    }
}