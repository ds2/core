/*
 * Copyright 2018 DS/2 <dstrauss@ds-2.de>
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
package ds2.oss.core.dbtools.it;

import javax.persistence.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ds2.oss.core.dbtools.converters.AbstractEntryStateConverter;
import ds2.oss.core.dbtools.it.entities.StateEntity;

/**
 * Created by dstrauss on 19.06.15.
 */
@Converter
public class MyStateConverter extends AbstractEntryStateConverter<StateEntity> {
    @PersistenceContext(unitName = "octest")
    private EntityManager em;
    
    @Override
    protected StateEntity getStateById(int id) {
        return em.find(StateEntity.class, id);
    }
}
