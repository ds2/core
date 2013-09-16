package ds2.oss.core.crypto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * Qualifier for a secure randomizer.
 * 
 * @author dstrauss
 * @version 0.3
 */
@Qualifier
@Target({ ElementType.FIELD, ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface SecureRandomizer {
}
