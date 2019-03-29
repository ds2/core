package ds2.oss.core.jee.jwt.api;

public interface TokenSigner {
    String getBase64UrlSignatureFrom(String headerDotBodyBase64UrlString) throws JwtContentException, JwtKeyException;

    Algorithm getAlgorithm();
}
