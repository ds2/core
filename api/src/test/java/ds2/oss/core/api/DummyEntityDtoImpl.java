/*
 * Copyright 2019 DS/2 <dstrauss@ds-2.de>
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
package ds2.oss.core.api;

import ds2.oss.core.api.persistence.AbstractEntityDtoConverterImpl;
import ds2.oss.core.api.persistence.CorePersistenceException;
import ds2.oss.core.api.persistence.InvalidEntityException;
import ds2.oss.core.api.persistence.OperationalContext;

public class DummyEntityDtoImpl extends AbstractEntityDtoConverterImpl<Long, IdAware<Long>, IdAware<Long>, CorePersistenceException> {

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
