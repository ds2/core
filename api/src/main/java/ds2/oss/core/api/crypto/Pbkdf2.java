package ds2.oss.core.api.crypto;

import javax.enterprise.context.Dependent;
import javax.inject.Qualifier;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Qualifier
@Dependent
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Pbkdf2 {
}
