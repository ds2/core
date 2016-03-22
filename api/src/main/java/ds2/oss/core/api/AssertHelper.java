package ds2.oss.core.api;

import java.util.Collection;

/**
 * Created by deindesign on 17.03.16.
 */
public interface AssertHelper {
    /**
     * Checks if a given object is not null.
     * @param o the object to test
     * @param errorMsg the error message to crash with
     */
    void assertNotNull(Object o, String errorMsg);

    /**
     * Checks if a given string is not empty/blank.
     * @param s the string to test
     * @param errorMsg the error message to crash with
     */
    void assertNotEmpty(String s, String errorMsg);

    /**
     * Checks if a given collection contains at least one item.
     * @param c the collection to test
     * @param errorMsg the error message to crash with
     */
    void assertNotEmpty(Collection<?> c, String errorMsg);
}
