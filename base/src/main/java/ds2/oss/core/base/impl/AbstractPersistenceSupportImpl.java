/**
 * 
 */
package ds2.oss.core.base.impl;

import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.Persistable;
import ds2.oss.core.api.PersistenceSupport;

/**
 * An abstract persistence support.
 * 
 * @author dstrauss
 * @version 0.3
 * @param <DTO>
 *            the dto type
 * @param <PRIMKEY>
 *            the primary key type
 */
public abstract class AbstractPersistenceSupportImpl<DTO extends Persistable<PRIMKEY>, PRIMKEY>
    implements
    PersistenceSupport<DTO, PRIMKEY> {
    /**
     * A logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    /**
     * Returns a list of a given query.
     * 
     * @param q
     *            the query
     * @param entityClass
     *            the entity class
     * @param <E>
     *            the entity type
     * @return the found results, or null if nothing was found
     */
    protected static <E> List<E> getSecureList(final Query q, final Class<E> entityClass) {
        List<E> rc = null;
        try {
            rc = q.getResultList();
        } catch (final IllegalStateException e) {
            LOG.debug("Error when executing the given query! Returning null.", e);
        }
        return rc;
    }
    
    /**
     * Returns a single item from a given query.
     * 
     * @param q
     *            the query
     * @param entityClass
     *            the target entity class
     * @param <E>
     *            the entity type
     * @return the found item, or null if not found
     */
    protected static <E> E getSecureSingle(final Query q, final Class<E> entityClass) {
        E rc = null;
        try {
            rc = entityClass.cast(q.getSingleResult());
        } catch (final IllegalStateException e) {
            LOG.debug("Error when executing the given query! Returning null.", e);
        } catch (final NoResultException e) {
            LOG.debug("Given query returned no result! Ignoring.", e);
        } catch (final NonUniqueResultException e) {
            LOG.warn("Given query does not return one but several results! Returning only first item.", e);
            rc = getSecureList(q, entityClass).get(0);
        }
        return rc;
    }
}
