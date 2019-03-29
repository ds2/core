package ds2.oss.core.jee.jwt;

import ds2.oss.core.jee.jwt.api.Algorithm;
import ds2.oss.core.jee.jwt.api.TokenSigner;
import ds2.oss.core.jee.jwt.impl.Base64UrlConverter;
import ds2.oss.core.jee.jwt.impl.HmacShaSignerImpl;
import ds2.oss.core.jee.jwt.impl.NoneSigner;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;

public class SignerBuilder {
    public static TokenSigner hs256(Key secretKey) {
        return new HmacShaSignerImpl(Algorithm.HMAC_SHA256, secretKey);
    }

    public static TokenSigner hs256(String secretKeyBase64) {
        Base64UrlConverter base64UrlConverter = new Base64UrlConverter();
        SecretKeySpec spec = new SecretKeySpec(base64UrlConverter.decodeFromBase64(secretKeyBase64), Algorithm.HMAC_SHA256.getMacName());
        return hs256(spec);
    }

    public static TokenSigner hs256FromString(String simpleByteString) {
        SecretKeySpec spec = new SecretKeySpec(simpleByteString.getBytes(StandardCharsets.UTF_8), Algorithm.HMAC_SHA256.getMacName());
        return hs256(spec);
    }

    public static TokenSigner dynamic(Algorithm algorithm, Key key) {
        TokenSigner signer = none();
        switch (algorithm) {
            case HMAC_SHA256:
                signer = hs256(key);
                break;
            case HS384:
                signer = hs384(key);
                break;
            case HS512:
                signer = hs512(key);
                break;
        }
        return signer;
    }

    private static TokenSigner hs512(Key key) {
        return new HmacShaSignerImpl(Algorithm.HS512, key);
    }

    private static TokenSigner hs384(Key key) {
        return new HmacShaSignerImpl(Algorithm.HS384, key);
    }

    private static TokenSigner none() {
        return new NoneSigner();
    }
}
