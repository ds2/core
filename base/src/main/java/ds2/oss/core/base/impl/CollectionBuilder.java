package ds2.oss.core.base.impl;

import java.lang.invoke.MethodHandles;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Idea for a collection builder.
 * 
 * @version 0.3
 * @author dstrauss
 * @param <E>
 *            the collection type
 * @param <Z>
 *            the itemtype
 */
public final class CollectionBuilder<E extends Collection<Z>, Z> implements CollectionBuilderContract<E, Z> {
    /**
     * A logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    /**
     * The collection impl.
     */
    private E collection;
    /**
     * Flag to indicate to allow adding null values.
     */
    private boolean allowNull = true;
    
    private CollectionBuilder(final Class<E> colClass) {
        try {
            collection = colClass.newInstance();
        } catch (InstantiationException e) {
            LOG.error("Error when instantiating the given collection class!", e);
        } catch (IllegalAccessException e) {
            LOG.error("Cannot access the default constructor of the given collection class!", e);
        }
    }
    
    private CollectionBuilder(final E col) {
        collection = col;
    }
    
    public static <E extends Collection<Z>, Z> CollectionBuilderContract<E, Z> create(final Class<E> c) {
        CollectionBuilder rc = new CollectionBuilder(c);
        return rc;
    }
    
    public static <E extends Collection<Z>, Z> CollectionBuilderContract<E, Z> create(final E c) {
        CollectionBuilder rc = new CollectionBuilder(c);
        return rc;
    }
    
    @Override
    public CollectionBuilderContract<E, Z> allowNull(final boolean b) {
        this.allowNull = b;
        return this;
    }
    
    @Override
    public CollectionBuilderContract<E, Z> add(final Z z) {
        if (allowNull || (z != null)) {
            collection.add(z);
        }
        return this;
    }
    
    @Override
    public CollectionBuilderContract<E, Z> addAt(final int index, final Z z) {
        if (collection instanceof List) {
            List<Z> z2 = (List<Z>) collection;
            z2.add(index, z);
        } else {
            LOG.warn("Current instance is not a list! Ignoring index.");
            collection.add(z);
        }
        return this;
    }
    
    @Override
    public CollectionBuilderContract<E, Z> remove(final Z z) {
        collection.remove(z);
        return this;
    }
    
    @Override
    public E build() {
        return collection;
    }
}
