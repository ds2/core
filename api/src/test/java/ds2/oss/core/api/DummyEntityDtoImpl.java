package ds2.oss.core.api;

import ds2.oss.core.api.persistence.AbstractEntityDtoConverterImpl;
import ds2.oss.core.api.persistence.InvalidEntityException;
import ds2.oss.core.api.persistence.OperationalContext;

public class DummyEntityDtoImpl extends AbstractEntityDtoConverterImpl<Long, IdAware<Long>, IdAware<Long>> {

    @Override
    protected void enrichEntity(IdAware<Long> longIdAware, IdAware<Long> longIdAware2, OperationalContext context) throws InvalidEntityException {

    }

    @Override
    protected IdAware<Long> createNewEntityInstance() {
        return null;
    }

    @Override
    protected IdAware<Long> enrichDto(IdAware<Long> longIdAware) {
        return null;
    }

}
