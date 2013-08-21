package ds2.oss.core.api.crypto;

import javax.crypto.SecretKey;

/**
 * To generate some keys.
 */
public interface KeyGeneratorService {
  SecretKey generate(int length, KeyGeneratorNames name);
  SecretKey generate(String pw, KeyGeneratorNames name);
}
