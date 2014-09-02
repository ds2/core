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
package ds2.oss.core.api;

import java.nio.charset.Charset;

import javax.crypto.SecretKey;

/**
 * A service to read a salt value and a IV from a specific file location, and provides this
 * information to the application server.
 *
 * @author dstrauss
 * @version 0.3
 */
public interface AppServerSecurityBaseDataService extends SecurityBaseData {
    
    /**
     * The system property name.
     */
    String SYS_PROPERTY = "ds2.app.sec.home";
    
    /**
     * Creates new salt value, new init vector, resets iteration count, creates new AES key.
     */
    void createData();
    
    /**
     * Stores the current value of the salt and init vector.
     *
     * @param cs
     *            the charset to use
     */
    void storeData(Charset cs);
    
    /**
     * Returns the appserver's current secret key.
     * 
     * @return the secret key
     */
    SecretKey getAppserverSecretKey();
}
