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
  private byte[] salt;

  public SecurityBaseDataImpl() {
    random = new SecureRandom();
  }

  @PostConstruct
  public void onClass() {
    salt = new byte[512];
    random.nextBytes(salt);
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
