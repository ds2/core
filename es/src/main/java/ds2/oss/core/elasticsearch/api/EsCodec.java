/**
 * 
 */
package ds2.oss.core.elasticsearch.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.context.ApplicationScoped;

/**
 * Marks a codec.
 * 
 * @author dstrauss
 * @version 0.2
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@ApplicationScoped
public @interface EsCodec {
    Class<?> value();
}
