/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package ds2.oss.core.api.persistence;

import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.IdAware;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Helper class to write entity dto handlers. This implementation will not perform any entity operation.
 *
 * @param <PKTYPE> the primary key type
 * @param <ENTITY> the entity type
 * @param <DTO>    the dto type
 */
public abstract class AbstractEntityDtoConverterImpl<PKTYPE, ENTITY extends IdAware<PKTYPE>, DTO extends IdAware<PKTYPE>, EX extends CoreException> implements EntityDtoHelper<PKTYPE, ENTITY, DTO, EX> {

    protected boolean returnEmptyArrayOnNullCollection;

    @Override
    public ENTITY createEntityFromDto(DTO dto, OperationalContext context) throws EX {
        ENTITY entity = createNewEntityInstance();
        enrichEntity(dto, entity, context);
        validateEntity(entity);
        return entity;
    }

    /**
     * Perform some dummy validation. Can throw a runtime exception.
     *
     * @param entity the entity to validate
     */
    protected void validateEntity(ENTITY entity) throws InvalidEntityException, EX {

    }

    @Override
    public ENTITY updateEntity(ENTITY foundEntity, DTO delta, OperationalContext context) throws InvalidEntityException, EX {
        if (foundEntity != null) {
            enrichEntity(delta, foundEntity, context);
            validateEntity(foundEntity);
        } else {
            onNotFoundEntity(null);
        }
        return foundEntity;
    }

    /**
     * Actions to perform if the given entity could not be found. Implementations can override this behavior.
     *
     * @param id the id of the entity
     * @throws EntityNotFoundException if not found.
     */
    protected void onNotFoundEntity(PKTYPE id) throws EntityNotFoundException {
        throw new EntityNotFoundException("Could not find entity with id of " + id);
    }

    protected abstract void enrichEntity(DTO dto, ENTITY entity, OperationalContext context) throws InvalidEntityException, EX;

    /**
     * Returns a new instance of this entity. Typically, this will return something like  new ENTITY() but
     * if you need some tweaking...
     *
     * @return the entity instance to use
     */
    protected abstract ENTITY createNewEntityInstance();

    @Override
    public DTO toDto(ENTITY entity) {
        if (entity == null) {
            return null;
        }
        DTO dto = enrichDto(entity);
        return dto;
    }

    @Override
    public List<DTO> toDtos(Collection<ENTITY> collection) {
        if (collection == null) {
            return returnEmptyArrayOnNullCollection ? new ArrayList<>(0) : null;
        }
        return collection.stream().map(entity -> toDto(entity)).collect(Collectors.toList());
    }

    protected abstract DTO enrichDto(ENTITY entity);
}
