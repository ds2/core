package ds2.oss.core.crypto;

import ds2.oss.core.api.crypto.BytesProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.util.Random;

/**
 * Created by dstrauss on 17.09.13.
 */
@Alternative
@ApplicationScoped
public class SimpleBytesProvider implements BytesProvider{
  @Inject
  private Random zufall;
  @Override
  public byte[] createRandomByteArray(int size) {
    if(size<=0){
      return null;
    }
    byte[] rc=new byte[size];
    zufall.nextBytes(rc);
    return rc;
  }
}
