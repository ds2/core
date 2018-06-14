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
package ds2.oss.core.crypto;

import ds2.oss.core.api.CoreConfiguration;
import ds2.oss.core.api.CoreErrors;
import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.Validate;
import ds2.oss.core.api.annotations.SecureRandomizer;
import ds2.oss.core.api.crypto.*;
import ds2.oss.core.api.maths.WeierstrassCurveData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.lang.invoke.MethodHandles;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by deindesign on 11.03.16.
 */
@ApplicationScoped
public class KeyPairGeneratorServiceImpl implements KeyPairGeneratorService {
    private static final Logger LOG= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    /**
     * The map containing the generators.
     */
    private Map<String, KeyPairGenerator> gens=new HashMap<>();
    private static final Lock LOCK=new ReentrantLock();
    @Inject
    private Validate val;
    @Inject
    private SecurityInstanceProvider secProv;
    @Inject
    @SecureRandomizer
    private SecureRandom randomizer;
    @Inject
    private CoreConfiguration config;
    @Inject
    private EllipticCurveSupport ecSupport;

    @Override
    public KeyPair generate(int bitSize, AlgorithmNamed alg) throws CoreException {
        return generateKeyPairCommon(bitSize, null, alg);
    }

    @Override
    public KeyPair generateRsaKey(int bitSize) throws CoreException {
        return generate(bitSize, KeyPairGenAlgorithm.RSA);
    }

    @Override
    public KeyPair generateEcKey(int bitSize, String curveName) throws CoreException {
        ECGenParameterSpec params=new ECGenParameterSpec(curveName);
        return generateKeyPairCommon(bitSize, params, KeyPairGenAlgorithm.EC);
    }

    @Override
    public KeyPair generateEcKey(int bitSize, EllipticCurveCryptoData data) throws CoreException {
        ECField field=new ECFieldFp(data.getPrime());
        BigInteger a=data.getA();
        BigInteger b=data.getB();
        EllipticCurve curve=new EllipticCurve(field, a, b);
        ECPoint pointOnCurve=new ECPoint(data.getGx(), data.getGy());
        ECParameterSpec spec=new ECParameterSpec(curve,pointOnCurve, data.getQ(), 1);
        return generateKeyPairCommon(bitSize, spec, KeyPairGenAlgorithm.EC);
    }

    @Override
    public KeyPair generateEcKey(int bitSize, WeierstrassBasedCryptoData wscd) throws CoreException {
        ECParameterSpec param=ecSupport.createFromData(wscd);
        return generateKeyPairCommon(bitSize, param, KeyPairGenAlgorithm.EC);
    }

    @Override
    public KeyPair generateEcKey(int bitSize, ECMontgomeryCurveCryptoData wscd) throws CoreException {
        ECParameterSpec param=ecSupport.createFromData(wscd);
        return generateKeyPairCommon(bitSize, param, KeyPairGenAlgorithm.EC);
    }

    @Override
    public KeyPair generateEcKey(int bitSize) throws CoreException {
        int m = 0;
        return generateKeyPairCommon(bitSize, null, KeyPairGenAlgorithm.EC);
    }

    private KeyPair generateKeyPairCommon(int bitSize, AlgorithmParameterSpec params, AlgorithmNamed alg) throws CoreException {
        try {
            if(LOCK.tryLock(config.getMethodLockTimeout(), TimeUnit.SECONDS)) {
                LOG.debug("Preparing keygen with bitSize={}", new Object[]{bitSize});
                KeyPair rc = null;
                KeyPairGenerator gen = gens.get(alg.getAlgorithmName());
                if (gen == null) {
                    gen = secProv.createKeyPairGenerator(alg);
                    if (gen == null) {
                        throw new CoreException(CoreErrors.NoGeneratorFound, "Could not find keypair generator with alg " + alg + "!");
                    }
                    gens.put(alg.getAlgorithmName(), gen);
                }
                if (params != null) {
                    gen.initialize(params, randomizer);
                    RSAKeyGenParameterSpec spec = new RSAKeyGenParameterSpec(bitSize, RSAKeyGenParameterSpec.F4);
                } else {
                    gen.initialize(bitSize, randomizer);
                }
                rc = gen.generateKeyPair();
                return rc;
            }
        } catch(InvalidParameterException e){
            throw new CoreException(CoreErrors.IllegalArgument, "The bit size "+bitSize+" is wrong!", e);
        } catch (InterruptedException e) {
            throw new CoreException(CoreErrors.LockingFailed, "We could not lock the keygen in time.", e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new CoreException(CoreErrors.IllegalArgument, "The parameters are incorrect!", e);
        } finally {
            LOG.debug("Unlocking method");
            LOCK.unlock();
        }
        return null;
    }
}
