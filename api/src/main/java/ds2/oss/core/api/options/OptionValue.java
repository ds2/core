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
package ds2.oss.core.api.options;

import ds2.oss.core.api.CreatedByModifiedByAware;
import ds2.oss.core.api.IdAware;
import ds2.oss.core.api.LifeCycleAware;
import ds2.oss.core.api.crypto.IvEncodedContent;

/**
 * A single option value.
 *
 * @param <E> the persistence type
 * @param <V> the value type of this value
 * @author dstrauss
 * @version 0.3
 */
public interface OptionValue<E, V>
        extends
        IdAware<E>,
        CreatedByModifiedByAware,
        LifeCycleAware,
        OptionValueContext,
        IvEncodedContent {

    /**
     * Returns the name or identifier of the approver of this option value.
     *
     * @return the identifier of the approver. May return null if not yet approved
     */
    String getApproverName();

    /**
     * Returns the name or identifier of the author of this option value.
     *
     * @return the identifier
     */
    String getAuthorName();

    /**
     * Returns the referenced option id.
     *
     * @return the referenced option id
     */
    E getOptionReference();

    /**
     * Returns the current stage of this value.
     *
     * @return the stage of this value
     */
    OptionValueStage getStage();

    /**
     * Returns the value of this option value object in case the option was encrypted.
     *
     * @return the value
     */
    V getUnencryptedValue();

    /**
     * Returns the value of this option value object.
     *
     * @return the value
     */
    V getValue();

    /**
     * Returns the value type being used by this option value.
     *
     * @return the value type
     */
    ValueType getValueType();

    /**
     * A dummy method to check if this value is encoded.
     *
     * @return TRUE if so, otherwise and by default FALSE
     */
    boolean isEncrypted();
}
