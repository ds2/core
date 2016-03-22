package ds2.oss.core.api;

/**
 * Created by deindesign on 17.03.16.
 */
public interface AssertHelper {
    void assertNotNull(Object o, String errorMsg);

    void assertNotEmpty(String s, String errorMsg);
}
