/**
 * 
 */
package ds2.oss.core.api.es;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * Marks an {@link ds2.oss.core.api.InfinispanStore} interface to be a store.
 * 
 * @author dstrauss
 * @version 0.3
 */
@Qualifier
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InfinispanStore {
    
}
