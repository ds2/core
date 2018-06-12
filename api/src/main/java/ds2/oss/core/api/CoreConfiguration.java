package ds2.oss.core.api;

/**
 * Created by deindesign on 21.03.16.
 */
public interface CoreConfiguration {
    /**
     * Returns the method lock timeout, in seconds.
     * @return the seconds to wait for a method lock.
     */
    int getMethodLockTimeout();
}
