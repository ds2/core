package ds2.oss.core.api.persistence;

import ds2.oss.core.api.IdAware;

import java.util.Collection;
import java.util.List;

public interface EntityDtoHelper<PKTYPE, ENTITY extends IdAware<PKTYPE>, DTO extends IdAware<PKTYPE>> {
    ENTITY createEntityFromDto(DTO dto, OperationalContext context);

    ENTITY updateEntity(ENTITY foundEntity, DTO delta, OperationalContext context) throws InvalidEntityException;

    DTO toDto(ENTITY entity);

    List<DTO> toDtos(Collection<ENTITY> collection);

}
