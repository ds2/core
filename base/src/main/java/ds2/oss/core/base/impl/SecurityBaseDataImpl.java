package ds2.oss.core.base.impl;

import ds2.oss.core.api.SecurityBaseData;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.security.SecureRandom;

/**
 * Dummy alternative for the sec base data.
 */
@Alternative
@ApplicationScoped
public class SecurityBaseDataImpl implements SecurityBaseData {
  /**
   * A randomizer.
   */
  private SecureRandom random;
  /**
   * A randomly generated salt value. On every restart of the component, a new salt value will be generated!
   */
  private byte[] salt;

  public SecurityBaseDataImpl() {
    random = new SecureRandom();
  }

  @PostConstruct
  public void onClass() {
    salt = random.generateSeed(512);
  }

  @Override
  public byte[] getSalt() {
    return salt;
  }

  @Override
  public int getMinIteration() {
    return 1000;
  }
}
