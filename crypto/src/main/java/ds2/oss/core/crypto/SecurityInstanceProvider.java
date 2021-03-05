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
package ds2.oss.core.crypto;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;

import ds2.oss.core.api.crypto.AlgorithmNamed;
import ds2.oss.core.api.crypto.Ciphers;
import ds2.oss.core.api.crypto.KeyGeneratorNames;
import ds2.oss.core.api.crypto.KeyPairGenAlgorithm;

import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.SecureRandom;

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
    Cipher createCipherInstance(AlgorithmNamed c);

    /**
     * Creates a key generator.
     *
     * @param name
     *            the key generator name
     * @return the instance, or null if an error occurred
     */
    KeyGenerator createKeyGenerator(AlgorithmNamed name);

    /**
     * Creates a kp generator.
     * @param alg the algorithm to use
     * @return the instance, or null if an error occurred
     */
    KeyPairGenerator createKeyPairGenerator(AlgorithmNamed alg);

    /**
     * Creates a secret key factory.
     *
     * @param string
     *            the name of the algorithm
     * @return the skf, or null if an error occurred
     */
    SecretKeyFactory createSecretKeyFactoryInstance(String string);

    KeyAgreement createKeyAgreement(AlgorithmNamed alg);

    SecureRandom createSecureRandom(AlgorithmNamed alg);

    KeyFactory createKeyFactory(AlgorithmNamed alg);

    /**
     * Creates a message digest with the given algorithm.
     * @param alg the algorithm
     * @return the message digest, or null if an error occurred
     */
    MessageDigest createMessageDigest(AlgorithmNamed alg);
}
