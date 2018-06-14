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
package ds2.oss.core.options.impl.identifiers;

import ds2.oss.core.api.options.ValueType;

/**
 * A string based option identifier.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class StringOptionIdentifier extends AbstractOptionIdentifier<String> {
    /**
     * The svuid.
     */
    private static final long serialVersionUID = -1717288216958232837L;
    
    /**
     * Inits the option.
     * 
     * @param appName
     *            the application name
     * @param optName
     *            the option name
     */
    public StringOptionIdentifier(final String appName, final String optName) {
        super(appName, optName, ValueType.STRING);
    }
    
    /**
     * Inits the option.
     * 
     * @param appName
     *            the application name
     * @param optName
     *            the option name
     * @param descr
     *            the description of this option
     */
    public StringOptionIdentifier(final String appName, final String optName, final String descr) {
        this(appName, optName);
        setDescription(descr);
    }
}
