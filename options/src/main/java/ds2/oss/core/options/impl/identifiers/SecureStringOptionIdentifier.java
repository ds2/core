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
package ds2.oss.core.options.impl.identifiers;

/**
 * A secure string option identifier.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class SecureStringOptionIdentifier extends StringOptionIdentifier {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 5223689707309029993L;
    
    /**
     * Inits the option identifier.
     * 
     * @param appName
     *            the app name
     * @param optName
     *            the option name
     */
    public SecureStringOptionIdentifier(final String appName, final String optName) {
        super(appName, optName);
    }
    
    /**
     * Inits the option identifier.
     * 
     * @param appName
     *            the app name
     * @param optName
     *            the option name
     * @param descr
     *            a description of the option
     */
    public SecureStringOptionIdentifier(final String appName, final String optName, final String descr) {
        super(appName, optName, descr);
    }
    
    @Override
    public boolean isEncrypted() {
        return true;
    }
}
