/**
 * 
 */
package ds2.oss.core.api;

/**
 * A persistence support contract.
 * 
 * @author dstrauss
 * @param <T>
 *            the entity type
 * @param <E>
 *            the entity primary key type
 * @version 0.2
 */
public interface PersistenceSupport<T extends Persistable<E>, E> {
    void persist(T t);
    
    T getById(E e);
}
