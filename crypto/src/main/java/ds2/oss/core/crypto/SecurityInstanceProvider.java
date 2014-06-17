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
package ds2.oss.core.crypto;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;

import ds2.oss.core.api.crypto.Ciphers;
import ds2.oss.core.api.crypto.KeyGeneratorNames;

/**
 * A contract for some provider based instances.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface SecurityInstanceProvider {
    /**
     * Creates a ciphers object.
     * 
     * @param c
     *            the cipher to create
     * 
     * @return the cipher, or null if an error occurred.
     */
    Cipher createCipherInstance(Ciphers c);
    
    /**
     * Creates a key generator.
     * 
     * @param name
     *            the key generator name
     * @return the instance, or null if an error occurred
     */
    KeyGenerator createKeyGenerator(KeyGeneratorNames name);
    
    /**
     * Creates a secret key factory.
     * 
     * @param string
     *            the name of the algorithm
     * @return the skf, or null if an error occurred
     */
    SecretKeyFactory createSecretKeyFactoryInstance(String string);
}
