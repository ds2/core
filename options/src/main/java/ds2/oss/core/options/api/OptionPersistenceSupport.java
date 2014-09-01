/*
l * Copyright 2012-2014 Dirk Strauss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ds2.oss.core.options.api;

import ds2.oss.core.api.PersistenceSupport;
import ds2.oss.core.api.dto.impl.OptionDto;
import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.api.options.OptionStage;

/**
 * The contract for an options persistence support.
 * 
 * @author dstrauss
 * @version 0.3
 * @param <K>
 *            the primary key type
 */
public interface OptionPersistenceSupport<K> extends PersistenceSupport<OptionDto<K, ?>, K> {
    // nothing special to do
    /**
     * Finds an option with the given data.
     * 
     * @param ident
     *            the option identifier
     * @param <V>
     *            the value type of the option
     * @return the found option, or null
     */
    <V> OptionDto<K, V> findOptionByIdentifier(OptionIdentifier<V> ident);
    
    /**
     * Sets a new option stage for a found option.
     * 
     * @param ident
     *            the option identifier
     * @param newStage
     *            the new stage
     * @param <V>
     *            the value type of the option
     * @return the updated option
     */
    <V> OptionDto<K, V> setOptionStage(OptionIdentifier<V> ident, OptionStage newStage);
}
