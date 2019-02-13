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
package ds2.oss.core.options.impl.identifiers;

import java.net.URL;

import ds2.oss.core.api.options.ValueType;

/**
 * A url option identifier.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class UrlOptionIdentifier extends AbstractOptionIdentifier<URL> {
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 5641998855769963068L;
    
    /**
     * Inits the class.
     * 
     * @param appName
     *            the application name
     * @param optName
     *            the option name
     */
    public UrlOptionIdentifier(final String appName, final String optName) {
        super(appName, optName, ValueType.URL);
    }
}
