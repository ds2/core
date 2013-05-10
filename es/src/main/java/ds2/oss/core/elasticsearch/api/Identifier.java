/**
 * 
 */
package ds2.oss.core.elasticsearch.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a field to be the identifier of a dto object.
 * 
 * @author dstrauss
 * @version 0.2
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface Identifier {
    
}
