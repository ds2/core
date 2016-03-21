package ds2.oss.core.crypto;

import ds2.oss.core.api.CoreConfiguration;
import ds2.oss.core.api.CoreErrors;
import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.Validate;
import ds2.oss.core.api.annotations.SecureRandomizer;
import ds2.oss.core.api.crypto.AlgorithmNamed;
import ds2.oss.core.api.crypto.KeyPairGenAlgorithm;
import ds2.oss.core.api.crypto.KeyPairGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.lang.invoke.MethodHandles;
import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
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

    @Override
    public KeyPair generate(int bitSize, AlgorithmNamed alg) throws CoreException {
        try {
            LOCK.tryLock(config.getMethodLockTimeout(), TimeUnit.SECONDS);
            LOG.debug("Preparing keygen with bitSize={} and alg={}", new Object[]{bitSize, alg});
            KeyPair rc = null;
            KeyPairGenerator gen = gens.get(alg.getAlgorithmName());
            if (gen == null) {
                gen = secProv.createKeyPairGenerator(alg);
                if (gen == null) {
                    throw new CoreException(CoreErrors.NoGeneratorFound, "Could not find keypair generator with alg " + alg + "!");
                }
                gens.put(alg.getAlgorithmName(), gen);
            }
            gen.initialize(bitSize, randomizer);
            rc = gen.generateKeyPair();
            return rc;
        } catch(InvalidParameterException e){
            throw new CoreException(CoreErrors.IllegalArgument, "The bit size "+bitSize+" is wrong!", e);
        } catch (InterruptedException e) {
            throw new CoreException(CoreErrors.LockingFailed, "We could not lock the keygen in time.", e);
        } finally {
            LOG.debug("Unlocking method");
            LOCK.unlock();
        }
    }
}
