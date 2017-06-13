package ds2.oss.core.api;

/**
 * Some kind of precondition service.
 */
public interface ValidationService<E extends Exception> {
    <O> O checkNotNull(O obj, String errorMsg) throws E;

    String checkNotEmpty(String s, String errorMsg) throws E;
}
