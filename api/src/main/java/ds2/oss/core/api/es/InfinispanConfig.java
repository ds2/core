package ds2.oss.core.api.es;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.context.Dependent;
import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;

/**
 * Injection qualifier.
 * 
 * @version 0.3
 * @author dstrauss
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Qualifier
@Dependent
public @interface InfinispanConfig {
    /**
     * The name of the xml file that contains the cache config. Default is infinispan.xml.
     */
    @Nonbinding
    String xmlFile() default "infinispan.xml";
    
    /**
     * The name of the cache to use. Empty string means default cache.
     */
    @Nonbinding
    String cacheName() default "";
    
}
