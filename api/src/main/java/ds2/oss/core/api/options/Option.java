/*
 * Copyright 2012-2014 Dirk Strauss
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
package ds2.oss.core.api.options;

import ds2.oss.core.api.CreatedModifiedAware;
import ds2.oss.core.api.Persistable;
import ds2.oss.core.api.crypto.IvEncodedContent;

/**
 * The definition of a single option.
 * 
 * @author dstrauss
 * @version 0.3
 * @param <E>
 *            the persistable type
 * @param <V>
 *            the value type of the option
 */
public interface Option<E, V> extends Persistable<E>, OptionIdentifier<V>, CreatedModifiedAware, IvEncodedContent {
    /**
     * Returns the default value of the option. If the option is usually encrypted, this will return
     * the encrypted value.
     * 
     * @return the default value
     */
    V getDefaultValue();
    
    /**
     * Returns the name or identifier of the option.
     * 
     * @return the identifier of the author/modifier
     */
    String getModifierName();
    
    /**
     * Returns the stage of the option.
     * 
     * @return the stage of the opion
     */
    OptionStage getStage();
    
    /**
     * Returns the decrypted value of the option if this option is encrypted. It is required that
     * implementations of this method must not ship this field value except the internal
     * application. That means that this value must not be serializable or transmittable in any kind
     * (xml, json, ...).
     * 
     * @return the decrypted value
     */
    V getDecryptedValue();
    
}
