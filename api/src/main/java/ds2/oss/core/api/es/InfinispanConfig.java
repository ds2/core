package ds2.oss.core.api.es;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by dstrauss on 20.08.13.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@javax.inject.Qualifier
public @interface InfinispanConfig {
  /**
   * The name of the xml file that contains the cache config. Default is infinispan.xml.
   */
  String xmlFile() default "infinispan.xml";

  /**
   * The name of the cache to use. Empty string means default cache.
   */
  String cacheName() default "";
}
