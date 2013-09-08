/**
 * 
 */
package ds2.oss.core.options.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * Flags an implementation to be able to persist options.
 * 
 * @author dstrauss
 * @version 0.3
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Qualifier
public @interface OptionsPersistenceSupport {
    /**
     * The type of the store.
     */
    StorageType type() default StorageType.NUMBERED;
    
    /**
     * The storage type.
     * 
     * @author dstrauss
     * @version 0.3
     */
    enum StorageType {
        /**
         * Persistence support uses Long as primary key identifiers.
         */
        NUMBERED,
        /**
         * Persistence support uses String values as primary key identifiers.
         */
        STRING;
    }
}
