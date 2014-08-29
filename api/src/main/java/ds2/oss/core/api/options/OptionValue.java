/*
 * Copyright 2012-2013 Dirk Strauss
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
import ds2.oss.core.api.LifeCycleAware;
import ds2.oss.core.api.Persistable;

/**
 * A single option value.
 * 
 * @author dstrauss
 * @version 0.3
 * @param <E>
 *            the persistence type
 * @param <V>
 *            the value type of this value
 */
public interface OptionValue<E, V> extends Persistable<E>, CreatedModifiedAware, LifeCycleAware, OptionValueContext {
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
     * Returns the value of this option value object.
     * 
     * @return the value
     */
    V getValue();
    
    /**
     * Returns the value of this option value object in case the option was encrypted.
     * 
     * @return the value
     */
    V getUnencryptedValue();
}
