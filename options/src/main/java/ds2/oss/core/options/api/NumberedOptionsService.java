/**
 * 
 */
package ds2.oss.core.options.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Qualifier;

/**
 * Dummy marker annotation to find the numbered options service.
 * 
 * @author dstrauss
 * @version 0.3
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@ApplicationScoped
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.METHOD })
@Deprecated
public @interface NumberedOptionsService {
    
}
