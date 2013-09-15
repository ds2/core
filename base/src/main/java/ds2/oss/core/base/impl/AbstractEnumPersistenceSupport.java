/**
 * 
 */
package ds2.oss.core.base.impl;

import java.util.HashMap;
import java.util.Map;

import ds2.oss.core.api.EnumPersistenceSupport;
import ds2.oss.core.api.NumericEnumValue;

/**
 * A helper implementation.
 * 
 * @author dstrauss
 * @param <E>
 *            the enum type
 * @version 0.3
 */
public abstract class AbstractEnumPersistenceSupport<E extends Enum<E> & NumericEnumValue>
    implements
    EnumPersistenceSupport<E> {
    /**
     * The lookup map.
     */
    private final Map<Integer, E> lookupMap;
    
    /**
     * Inits the impl.
     */
    protected AbstractEnumPersistenceSupport() {
        lookupMap = new HashMap<>();
    }
    
    /**
     * Fills all given values from the enum into the lookup map.
     * 
     * @param xs
     *            the values
     */
    @SafeVarargs
    protected final void fillLookup(final E... xs) {
        if ((xs == null) || (xs.length <= 0)) {
            return;
        }
        for (E x : xs) {
            final Integer i = Integer.valueOf(x.getNumericalValue());
            lookupMap.put(i, x);
        }
    }
    
    @Override
    public int toInt(final E e) {
        final NumericEnumValue nev = e;
        return nev.getNumericalValue();
    }
    
    @Override
    public E toValue(final int e) {
        return lookupMap.get(Integer.valueOf(e));
    }
    
}
