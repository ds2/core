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

import ds2.oss.core.api.dto.impl.OptionDto;
import ds2.oss.core.api.options.OptionIdentifier;

/**
 * Factory to create options. Implementations of this contract will return a dto object that matches
 * their persistence support.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface OptionFactory {
    /**
     * Creates a dto that can be used to get stored and loaded via its persistence support.
     * 
     * @param ident
     *            the option identifier
     * @param primaryKey
     *            the primary key for this option. Set to null if the persistence layer will setup
     *            an id.
     * @param defaultVal
     *            the default value for the option
     * @param <K>
     *            the primary key type
     * @param <V>
     *            the value type
     * @return the option implementation
     */
    <K, V> OptionDto<K, V> createOptionDto(OptionIdentifier<V> ident, K primaryKey, V defaultVal);
}
