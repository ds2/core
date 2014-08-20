package ds2.oss.core.base.impl;

import java.util.Collection;

/**
 * Contract for the collection builder.
 * 
 * @author dstrauss
 * @version 0.3
 * @param <E>
 *            the collection type
 * @param <Z>
 *            the item type
 */
public interface CollectionBuilderContract<E extends Collection<Z>, Z> {
    /**
     * Whether to allow adding null values.
     * 
     * @param b
     *            the flag value
     * @return this instance
     */
    CollectionBuilderContract<E, Z> allowNull(boolean b);
    
    /**
     * Adds an item to the collection.
     * 
     * @param z
     *            the item
     * @return this instance
     */
    CollectionBuilderContract<E, Z> add(Z z);
    
    /**
     * Adds an item at a specific position.
     * 
     * @param index
     *            the position
     * @param z
     *            the item
     * @return this instance
     */
    CollectionBuilderContract<E, Z> addAt(int index, Z z);
    
    /**
     * Removes an item.
     * 
     * @param z
     *            the item to remove
     * @return this instance
     */
    CollectionBuilderContract<E, Z> remove(Z z);
    
    /**
     * Creates the collection.
     * 
     * @return the collection
     */
    E build();
}
