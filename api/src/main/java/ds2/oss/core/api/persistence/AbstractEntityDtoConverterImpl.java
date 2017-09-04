package ds2.oss.core.api.persistence;

import ds2.oss.core.api.IdAware;

import javax.persistence.EntityManager;

public abstract class AbstractEntityDtoConverterImpl<PKTYPE, ENTITY extends IdAware<PKTYPE>, DTO extends IdAware<PKTYPE>> {

    public <E extends Exception> ENTITY createEntityFromDto(EntityManager em, DTO dto) throws E {
        ENTITY entity = createEntityInstance();
        enrichEntity(dto, entity);
        validateEntity(entity);
        JpaSupport.storeEntity(em, entity);
        return entity;
    }

    /**
     * Perform some dummy validation.
     *
     * @param entity the entity to validate
     */
    protected <E extends Exception> void validateEntity(ENTITY entity) throws E {

    }

    public <E extends Exception> ENTITY updateEntity(EntityManager em, Class<ENTITY> entityClass, PKTYPE id, DTO delta) throws E {
        ENTITY foundEntity = JpaSupport.findById(em, entityClass, id);
        if (foundEntity == null) {
            onNotFoundEntity(entityClass, id);
        }
        enrichEntity(delta, foundEntity);
        validateEntity(foundEntity);
        return foundEntity;
    }

    protected abstract <E extends Exception> void onNotFoundEntity(Class<ENTITY> entityClass, PKTYPE id) throws E;

    protected abstract <E extends Exception> void enrichEntity(DTO dto, ENTITY entity) throws E;

    /**
     * Returns a new instance of this entity. Typically, this will return something like  new ENTITY() but
     * if you need some tweaking...
     *
     * @return the entity instance to use
     */
    protected abstract ENTITY createEntityInstance();
}
