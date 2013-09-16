package ds2.oss.core.crypto;

import javax.enterprise.inject.Produces;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Created by dstrauss on 16.09.13.
 */
public class RandomProvider {
  @Produces
  @SecureRandomizer
  public Random createRandom() throws NoSuchAlgorithmException {
    Random rc= java.security.SecureRandom.getInstance("SHA1PRNG");
    return rc;
  }
}
