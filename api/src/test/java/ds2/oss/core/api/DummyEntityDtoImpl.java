package ds2.oss.core.api;

import ds2.oss.core.api.persistence.AbstractEntityDtoConverterImpl;
import ds2.oss.core.api.persistence.InvalidEntityException;

public class DummyEntityDtoImpl extends AbstractEntityDtoConverterImpl<Long, IdAware<Long>, IdAware<Long>> {

    @Override
    protected void enrichEntity(IdAware<Long> longIdAware, IdAware<Long> longIdAware2) throws InvalidEntityException {

    }

    @Override
    protected IdAware<Long> createEntityInstance() {
        return null;
    }
}
