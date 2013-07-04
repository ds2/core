package ds2.oss.core.api;

/**
 * Contract for sec base data.
 */
public interface SecurityBaseData {
    byte[] getSalt();
    int getMinIteration();
}
