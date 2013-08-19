/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.elasticsearch.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Attach this annotation to a property field of a java class to use it as _timestamp path field.
 * @author dstrauss
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface TimestampPath {
  //nothing special to do
}
