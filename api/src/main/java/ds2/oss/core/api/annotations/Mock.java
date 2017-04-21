package ds2.oss.core.api.annotations;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Stereotype;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by dstrauss on 20.04.17. Taken from CDI 1.2 API documentation.
 */
@Alternative
@Stereotype
@Target(TYPE)
@Retention(RUNTIME)
public @interface Mock {
}
