package ds2.oss.core.api.crypto;

import ds2.oss.core.api.CoreException;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.security.KeyPair;

/**
 * Created by deindesign on 11.03.16.
 */
public interface KeyPairGeneratorService {
    KeyPair generate(@Min(128)int bitSize, @NotNull AlgorithmNamed alg) throws CoreException;
}
