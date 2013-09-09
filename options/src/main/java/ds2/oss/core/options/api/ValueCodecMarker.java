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

import ds2.oss.core.api.options.ValueType;

/**
 * Marker annotation for a value codec.
 * 
 * @author dstrauss
 * @version 0.3
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ApplicationScoped
public @interface ValueCodecMarker {
    /**
     * The type for this value codec.
     */
    ValueType value();
}
