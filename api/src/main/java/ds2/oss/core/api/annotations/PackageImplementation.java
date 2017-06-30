package ds2.oss.core.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by dstrauss on 30.06.17.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.PACKAGE)
public @interface PackageImplementation {
    boolean addScmVersion() default false;

    String title() default "";

    String vendor() default "";

    String version() default "";

    boolean isAlsoSpecification() default false;
}
