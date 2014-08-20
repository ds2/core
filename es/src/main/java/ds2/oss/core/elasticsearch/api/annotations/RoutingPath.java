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
 *
 * @author dstrauss
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface RoutingPath {
  //nothing special to do
}
