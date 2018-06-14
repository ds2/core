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
package ds2.oss.core.dbtools.converters;

import ds2.oss.core.api.EntryState;
import ds2.oss.core.api.EntryStates;

import javax.persistence.AttributeConverter;

/**
 * Created by dstrauss on 19.06.15.
 */
public abstract class AbstractEntryStateConverter<E extends EntryState> implements AttributeConverter<E, Integer> {

    @Override
    public Integer convertToDatabaseColumn(final E attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getNumericalValue();
    }

    @Override
    public E convertToEntityAttribute(final Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return getStateById(dbData.intValue());
    }

    protected abstract E getStateById(int id);

}
