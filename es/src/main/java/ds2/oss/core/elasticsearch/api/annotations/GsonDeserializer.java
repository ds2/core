/**
 * 
 */
package ds2.oss.core.elasticsearch.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Qualifier;

/**
 * Marks a deserializer.
 * 
 * @author dstrauss
 * @version 0.2
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Qualifier
@ApplicationScoped
public @interface GsonDeserializer {
    /**
     * The dto class for this deserializer.
     */
    Class<?> value();
}
