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
package ds2.oss.core.options.api;

import ds2.oss.core.api.PersistenceSupport;
import ds2.oss.core.api.dto.impl.OptionValueDto;
import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.api.options.OptionValue;
import ds2.oss.core.api.options.OptionValueContext;
import ds2.oss.core.api.options.OptionValueStage;

/**
 * Contract to persist option values.
 * 
 * @author dstrauss
 * @version 0.3
 * @param <K>
 *            the key type for the dto
 *
 */
public interface OptionValuePersistenceSupport<K> extends PersistenceSupport<OptionValueDto<K, ?>, K> {
    /**
     * Sets a new stage for the given option value.
     * @param id the id of the option value
     * @param newStage the new stage value
     */
    void setStage(K id, OptionValueStage newStage);
    
    /**
     * Finds the best matching live option value for the given context.
     * @param <V> the value type
     * @param ident the option identifier
     * @param ctx the option value context
     * @return the found option value, or null if not found
     */
    <V> OptionValue<K, V> findBestOptionValue(OptionIdentifier<V> ident, OptionValueContext ctx);
}
