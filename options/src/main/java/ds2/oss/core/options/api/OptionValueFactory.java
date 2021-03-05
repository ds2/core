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
package ds2.oss.core.options.api;

import ds2.oss.core.api.dto.impl.OptionValueDto;
import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.api.options.OptionValueContext;

/**
 * A contract to create an option value dto.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface OptionValueFactory {
    /**
     * Creates an option value dto.
     * 
     * @param ident
     *            the option identifier
     * @param primaryKey
     *            the primary key. Leave to null to address creation to the persistence layer.
     * @param ctx
     *            the option value context
     * @param val
     *            the option value
     * @param <K>
     *            the key type
     * @param <V>
     *            the value type
     * @return the dto
     */
    <K, V> OptionValueDto<K, V> createOptionValueDto(OptionIdentifier<V> ident, K primaryKey, OptionValueContext ctx,
        V val);
}
