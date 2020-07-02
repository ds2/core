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
/**
 * 
 */
package ds2.oss.core.options.api;

import ds2.oss.core.api.options.OptionException;
import ds2.oss.core.api.options.OptionValueContext;
import ds2.oss.core.options.impl.entities.OptionValueContextEntity;

/**
 * A tool to work with sets of option value contexts.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface OptionValueContextFinder {
    /**
     * Finds the context entity with the given context.
     * 
     * @param ctx
     *            the context
     * @return the context entity, or null if not found
     */
    OptionValueContextEntity findByContext(OptionValueContext ctx);
    
    /**
     * Same as {@link #findByContext(OptionValueContext)} but will create the entity if not found.
     * 
     * @param ctx
     *            the context
     * @return the context entity
     * @throws OptionException
     *             if an error occurred
     */
    OptionValueContextEntity findOrCreateContext(OptionValueContext ctx) throws OptionException;
}
