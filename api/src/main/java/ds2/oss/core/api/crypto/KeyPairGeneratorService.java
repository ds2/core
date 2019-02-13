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
package ds2.oss.core.api.crypto;

import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.maths.WeierstrassCurveData;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.security.KeyPair;

/**
 * Created by deindesign on 11.03.16.
 */
public interface KeyPairGeneratorService {
    KeyPair generate(@Min(128)int bitSize, @NotNull AlgorithmNamed alg) throws CoreException;

    /**
     * Generates an RSA key pair.
     * @param bitSize the bit size
     * @return the generated key pair
     * @throws CoreException if an error occurred
     */
    KeyPair generateRsaKey(int bitSize) throws CoreException;

    /**
     * Generates an elliptic curve key pair.
     * @param bitSize the bit size
     * @param curveName the curve name
     * @return the generated key pair
     * @throws CoreException if an error occurred
     */
    KeyPair generateEcKey(int bitSize, String curveName) throws CoreException;

    KeyPair generateEcKey(int bitSize, EllipticCurveCryptoData data) throws CoreException;

    KeyPair generateEcKey(int bitSize, WeierstrassBasedCryptoData wscd) throws CoreException;

    KeyPair generateEcKey(int bitSize, ECMontgomeryCurveCryptoData wscd) throws CoreException;

    /**
     * Generates an EC key from a default curve given by the current provider.
     * @param bitSize the bit size to use
     * @return a keypair with some default key rules
     * @throws CoreException if an error occurred
     */
    KeyPair generateEcKey(int bitSize) throws CoreException;
}
