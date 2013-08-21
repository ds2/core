package ds2.oss.core.crypto;

import ds2.oss.core.api.crypto.KeyGeneratorNames;
import ds2.oss.core.api.crypto.KeyGeneratorService;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by dstrauss on 21.08.13.
 */
public class KeyGeneratorServiceImpl implements KeyGeneratorService{
  /**
   * A logger.
   */
  private static final Logger LOG= LoggerFactory.getLogger(KeyGeneratorServiceImpl.class);
  @Override
  public SecretKey generate(int length, KeyGeneratorNames name) {
    SecretKey rc=null;
    try {
      KeyGenerator kgInstance=KeyGenerator.getInstance(name.name(), new BouncyCastleProvider());
      kgInstance.init(length);
      rc=kgInstance.generateKey();
    } catch (NoSuchAlgorithmException e) {
      LOG.error("Given Algorithm is unknown!",e);
    }
    return rc;
  }

  @Override
  public SecretKey generate(String pw, KeyGeneratorNames name) {
    SecretKey rc=null;
    try {
      byte[] pwBytes=pw.getBytes("utf-8");
      SecretKeySpec secretKeySpec=new SecretKeySpec(pwBytes,name.name());
      return secretKeySpec;
    } catch (UnsupportedEncodingException e) {
      LOG.error("Unknown encoding!",e);
    }
    return rc;
  }
}
