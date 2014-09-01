/**
 * 
 */
package ds2.oss.core.api.options;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Qualifier;

/**
 * Marker annotation for any implementations and field injections to address a specific value type
 * behaviour.
 * 
 * @author dstrauss
 * @version 0.3
 */
@Qualifier
@ApplicationScoped
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ForValueType {
    /**
     * The value type.
     */
    ValueType value();
}
