package ds2.oss.core.api.crypto;

public interface HashedResult extends IvEncodedContent{
    String getAlgorithmName();
    int getRounds();
}
