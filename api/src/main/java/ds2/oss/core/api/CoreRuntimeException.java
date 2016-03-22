package ds2.oss.core.api;

/**
 * Created by deindesign on 22.03.16.
 */
public class CoreRuntimeException extends RuntimeException {
    /**
     * The error data.
     */
    private final IErrorData errorData;

    /**
     * Inits the exception with the given error data.
     *
     * @param d
     *            the error data
     */
    public CoreRuntimeException(final IErrorData d) {
        super("" + d);
        errorData = d;
    }

    /**
     * Inits the exception with the given error data and cause.
     *
     * @param d
     *            the error data
     * @param t
     *            the cause
     */
    public CoreRuntimeException(final IErrorData d, String s) {
        super(s);
        errorData = d;
    }

    /**
     * Inits the exception with the given error data and cause.
     *
     * @param d
     *            the error data
     * @param t
     *            the cause
     */
    public CoreRuntimeException(final IErrorData d, final Throwable t) {
        super("" + d, t);
        errorData = d;
    }

    public CoreRuntimeException(final IErrorData d, final String msg, final Throwable t){
        super(msg, t);
        errorData=d;
    }

    /**
     * Returns the error data.
     *
     * @return the error data
     */
    public IErrorData getErrorData() {
        return errorData;
    }
}
