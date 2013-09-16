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

import java.util.Date;
import java.util.List;

/**
 * A service to store and retrieve options and option values from various sources.
 * 
 * @author dstrauss
 * @version 0.3
 * @param <E>
 *            the type of the primary key
 */
public interface OptionStorageService<E> {
    
    /**
     * Returns the option with the given identifier.
     * 
     * @param ident
     *            the identifier
     * @param <V>
     *            the value type of the option
     * @return the option, or null if not found
     */
    <V> Option<E, V> getOptionByIdentifier(OptionIdentifier<V> ident);
    
    /**
     * Creates a new option value.
     * 
     * @param optionIdent
     *            the option identifier
     * @param ctx
     *            the option value context
     * @param scheduleDate
     *            the schedule date for this value
     * @param value
     *            the value of the option value
     * @param <V>
     *            the value type
     * @return the created option value
     */
    <V> OptionValue<E, V> createOptionValue(OptionIdentifier<V> optionIdent, OptionValueContext ctx, Date scheduleDate,
        V value);
    
    /**
     * Creates a new option.
     * 
     * @param ident
     *            the option identifier
     * @param val
     *            the value for the option
     * @param <V>
     *            the value type
     * @return the created option
     */
    <V> Option<E, V> createOption(OptionIdentifier<V> ident, V val);
    
    /**
     * Finds the best option value for the given context.
     * 
     * @param ident
     *            the option identifier
     * @param ctx
     *            the option value context
     * @param <V>
     *            the value type
     * @return the option value to use. This MAY return a virtual option value with the option.
     */
    <V> OptionValue<E, V> findBestOptionValueByContext(OptionIdentifier<V> ident, OptionValueContext ctx);
    
    /**
     * Returns all known options for the given application name.
     * 
     * @param appName
     *            the application name
     * @return A list of all known options.
     */
    List<Option<E, ?>> getAllOptions(String appName);
    
    /**
     * Sets the stage of an option.
     * 
     * @param endpoint
     *            the option identifier
     * @param <V>
     *            the value type of the option identifier
     * @param deleted
     *            the new stage
     * @return the updated option
     */
    <V> Option<E, V> setOptionStage(OptionIdentifier<V> endpoint, OptionStage deleted);
}
