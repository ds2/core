package ds2.oss.core.api.annotations;

import javax.enterprise.util.Nonbinding;
import javax.validation.constraints.Null;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by deindesign on 13.12.15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE })
public @interface PropertiesLoader {
    @Nonbinding
    String filePath() default "";
    @Nonbinding
    String sysProp() default "";
    @Nonbinding
    String envProp() default "";
    @Nonbinding
    boolean setNullOnFail() default false;
}
