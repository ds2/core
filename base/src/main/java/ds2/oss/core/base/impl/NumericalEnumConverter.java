/*
 * Copyright 2012-2014 Dirk Strauss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
 * @Deprecated Better use the JPA tools. See {@link javax.persistence.Converter}.
 */
@Deprecated
public class NumericalEnumConverter<E extends Enum<E>> {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
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
    private <T> E getByLookup(final String methodName, final Class<T> cT, final T val) {
        final Method m = getMethodWithSpecificParam(methodName, cT);
        if (m != null) {
            try {
                return c.cast(m.invoke(null, val));
            } catch (final IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                LOG.debug("Error when invoking!", e);
            }
        }
        return null;
    }
    
    /**
     * Returns the enum value for a given numerical value.
     * 
     * @param i
     *            the numerical value
     * @param methodName
     *            the name of the static method of the enum class which performs the lookup
     * @return the enum value, or null if not found
     */
    public final E getEnumByReflection(final int i, final String methodName) {
        LOG.debug("perform lookup of id {} via method {} in  {}", new Object[] { Integer.valueOf(i), methodName, c });
        E rc = null;
        try {
            rc = getByLookup(methodName, int.class, Integer.valueOf(i));
            if (rc == null) {
                rc = getByLookup(methodName, long.class, Long.valueOf(i));
            }
            if (rc == null) {
                LOG.warn("Method {} not defined in {}! Please verify lookup.", new Object[] { methodName, c });
            }
        } catch (final SecurityException | IllegalArgumentException e) {
            LOG.error("Error when looking up an enum value via reflection!", e);
        }
        return rc;
    }
    
    /**
     * Parses a given int value into an enum value via a reflection lookup.
     * 
     * @param i
     *            the id of the enum value
     * @param methodName
     *            the method name within the enum class to use for lookup
     * @param defValue
     *            the default value, if no value could be found
     * @return the default value, or the found value.
     */
    public final E getEnumByReflection(final int i, final String methodName, final E defValue) {
        E rc = getEnumByReflection(i, methodName);
        if (rc == null) {
            rc = defValue;
        }
        return rc;
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
    private Method getMethodWithSpecificParam(final String methodName, final Class<?>... type) {
        Method rc = null;
        try {
            rc = c.getDeclaredMethod(methodName, type);
        } catch (final SecurityException e) {
            LOG.debug("Error when looking up a specific method!", e);
        } catch (final NoSuchMethodException e) {
            LOG.debug("Method {} not found, ignoring", new Object[] { methodName, type });
        }
        return rc;
    }
    
    /**
     * Returns the numerical value for the given enum.
     * 
     * @param e
     *            the enum value
     * @return the numerical value to use
     */
    public final int getValue(final E e) {
        int rc = -1;
        if (e == null) {
            return rc;
        }
        if (e instanceof NumericEnumValue) {
            final NumericEnumValue nev = (NumericEnumValue) e;
            rc = nev.getNumericalValue();
        } else {
            rc = e.ordinal();
        }
        return rc;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("NumericalEnumConverter (c=");
        builder.append(c);
        builder.append(")");
        return builder.toString();
    }
}
