/**
 * 
 */
package ds2.oss.core.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Instructs the test environment to execu
 * 
 * @author dstrauss
 *         
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface ExecuteSqlBefore {
    /**
     * the sql files to execute before test.
     */
    String[]value();
}
