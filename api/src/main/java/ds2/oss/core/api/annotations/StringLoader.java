package ds2.oss.core.api.annotations;

import javax.enterprise.util.Nonbinding;

/**
 * To load string values from a property, an environment.
 *
 * @author dstrauss
 */
public @interface StringLoader {
    /**
     * A path from a system property.
     *
     * @return a system property name that contains the path
     */
    @Nonbinding
    String sysProp() default "";

    /**
     * A path from an environment variable. This is tried first.
     *
     * @return a path from an environment variable
     */
    @Nonbinding
    String envProp() default "";

    @Nonbinding
    String defaultValue() default "";

    @Nonbinding
    boolean setNullOnFail() default false;
}
