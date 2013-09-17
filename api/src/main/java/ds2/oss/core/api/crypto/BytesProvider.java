package ds2.oss.core.api.crypto;

/**
 * A bytes provider.
 * @author dstrauss
 * @version 0.3
 */
public interface BytesProvider {
  byte[] createRandomByteArray(int size);
}
