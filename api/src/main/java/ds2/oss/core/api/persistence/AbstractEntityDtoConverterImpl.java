package ds2.oss.core.api.persistence;

import ds2.oss.core.api.IdAware;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public abstract class AbstractEntityDtoConverterImpl<PKTYPE, ENTITY extends IdAware<PKTYPE>, DTO extends IdAware<PKTYPE>> {

    public ENTITY createEntityFromDto(EntityManager em, DTO dto) {
        ENTITY entity = createEntityInstance();
        enrichEntity(dto, entity);
        validateEntity(entity);
        JpaSupport.storeEntity(em, entity);
        return entity;
    }

    /**
     * Perform some dummy validation. Can throw a runtime exception.
     *
     * @param entity the entity to validate
     */
    protected void validateEntity(ENTITY entity) throws InvalidEntityException {

    }

    public ENTITY updateEntity(EntityManager em, Class<ENTITY> entityClass, PKTYPE id, DTO delta) throws InvalidEntityException {
        ENTITY foundEntity = JpaSupport.findById(em, entityClass, id);
        if (foundEntity == null) {
            onNotFoundEntity(entityClass, id);
        }
        enrichEntity(delta, foundEntity);
        validateEntity(foundEntity);
        return foundEntity;
    }

    /**
     * Actions to perform if the given entity could not be found. Implementations can override this behavior.
     *
     * @param entityClass the entity class
     * @param id          the id of the entity
     * @throws EntityNotFoundException if not found.
     */
    protected void onNotFoundEntity(Class<ENTITY> entityClass, PKTYPE id) throws EntityNotFoundException {
        throw new EntityNotFoundException("Could not find entity " + entityClass.getName() + " with id of " + id);
    }

    protected abstract void enrichEntity(DTO dto, ENTITY entity) throws InvalidEntityException;

    /**
     * Returns a new instance of this entity. Typically, this will return something like  new ENTITY() but
     * if you need some tweaking...
     *
     * @return the entity instance to use
     */
    protected abstract ENTITY createEntityInstance();
}
