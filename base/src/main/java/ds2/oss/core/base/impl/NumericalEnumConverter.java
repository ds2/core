/**
 * 
 */
package ds2.oss.core.base.impl;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.NumericEnumValue;

/**
 * A basic converter.
 * 
 * @author dstrauss
 * @param <E>
 *            The enum
 * @version 0.1
 */
public class NumericalEnumConverter<E extends Enum<?>> {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles
        .lookup().lookupClass());
    /**
     * The enum class.
     */
    private final Class<E> c;
    
    /**
     * Inits the converter.
     * 
     * @param c1
     *            The enum class
     * 
     * 
     */
    public NumericalEnumConverter(final Class<E> c1) {
        this.c = c1;
    }
    
    /**
     * Returns the numerical value for the given enum.
     * 
     * @param e
     *            the enum value
     * @return the numerical value to use
     */
    public final int getValue(final E e) {
        if (e == null) {
            return -1;
        }
        if (e instanceof NumericEnumValue) {
            final NumericEnumValue nev = (NumericEnumValue) e;
            return nev.getNumericalValue();
        }
        return e.ordinal();
    }
    
    /**
     * Returns the enum value for a given numerical value.
     * 
     * @param i
     *            the numerical value
     * @param methodName
     *            the name of the static method of the enum class which performs
     *            the lookup
     * @return the enum value, or null if not found
     */
    public E getEnumByReflection(final int i, final String methodName) {
        E rc = null;
        try {
            rc = getByLookup(methodName, int.class, i);
            if (rc == null) {
                rc = getByLookup(methodName, long.class, (long) i);
            }
        } catch (final SecurityException | IllegalArgumentException e) {
            LOG.error("Error when looking up an enum value via reflection!", e);
        }
        return rc;
    }
    
    /**
     * Performs an enum lookup with specific reflection parameters.
     * 
     * @param methodName
     *            the method name
     * @param cT
     *            the class of the parameter
     * @param val
     *            the value to use for lookup
     * @return the found enum value, or null if not found
     * @param <T>
     *            the type of the index value
     */
    private <T> E getByLookup(final String methodName, final Class<T> cT,
        final T val) {
        final Method m = getMethodWithSpecificParam(methodName, cT);
        if (m != null) {
            try {
                return (E) m.invoke(null, val);
            } catch (final IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
                LOG.debug("Error when invoking!", e);
            }
        }
        return null;
    }
    
    /**
     * Performs a lookup.
     * 
     * @param methodName
     *            the method name
     * @param type
     *            the class of the parameters
     * @return the found method
     */
    private Method getMethodWithSpecificParam(final String methodName,
        final Class<?>... type) {
        Method rc = null;
        try {
            rc = c.getDeclaredMethod(methodName, type);
        } catch (final NoSuchMethodException | SecurityException e) {
            LOG.debug("Error when looking up a specific method!", e);
        }
        return rc;
    }
}
