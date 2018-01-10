package ds2.oss.core.api;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * This contract is to be attached to typical service contracts in the backend/deep core layer.
 * Typically, this interface is attached to entity services.
 */
public interface JpaCrudFacade<PRIMKEY, E extends IdAware<PRIMKEY>> extends PersistenceSupport<E, PRIMKEY> {
    List<E> getAll(int offset, int size);

    List<E> getAllByQuery(int offset, int size, TypedQuery<E> query);

}
