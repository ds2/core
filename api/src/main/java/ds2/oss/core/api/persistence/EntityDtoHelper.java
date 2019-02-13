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
package ds2.oss.core.api.persistence;

import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.IdAware;

import java.util.Collection;
import java.util.List;

public interface EntityDtoHelper<PKTYPE, ENTITY extends IdAware<PKTYPE>, DTO extends IdAware<PKTYPE>, EX extends CoreException> {
    ENTITY createEntityFromDto(DTO dto, OperationalContext context) throws EX;

    ENTITY updateEntity(ENTITY foundEntity, DTO delta, OperationalContext context) throws InvalidEntityException, EX;

    DTO toDto(ENTITY entity);

    List<DTO> toDtos(Collection<ENTITY> collection);

}
