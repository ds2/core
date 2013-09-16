package ds2.oss.core.crypto;

import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by dstrauss on 16.09.13.
 */
@Qualifier
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER})
public @interface SecureRandomizer {
}
