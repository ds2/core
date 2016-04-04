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

    /**
     * Generates an EC key from a default curve given by the current provider.
     * @param bitSize the bit size to use
     * @return a keypair with some default key rules
     * @throws CoreException if an error occurred
     */
    KeyPair generateEcKey(int bitSize) throws CoreException;
}
