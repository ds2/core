/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.elasticsearch.api;

/**
 *
 * @author dstrauss
 */
public @interface FieldMapping {
  FieldTypes type() default FieldTypes.STRING;
          
}
