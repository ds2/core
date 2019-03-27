package ds2.oss.core.jee.jwt;

import ds2.oss.core.api.Base64Codec;
import ds2.oss.core.api.CodecException;
import ds2.oss.core.api.JsonCodec;
import ds2.oss.core.jee.jwt.dto.HeaderDto;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

@Dependent
@Setter
public class JwtServiceImpl {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Inject
    private JsonCodec jsonCodec;
    @Inject
    private Base64Codec base64Codec;

    public String createHeader(Algorithm algorithm, String type, String contentType) throws JwtContentException {
        HeaderDto headerDto = HeaderDto.builder().alg(algorithm.getFieldValue())
                .typ(type)
                .cty(contentType)
                .build();
        StringBuilder stringBuilder = new StringBuilder(200);
        try {
            String headerJson = jsonCodec.encode(headerDto);
            String headerBase64UrlEncoded = base64Codec.encode(headerJson.getBytes(StandardCharsets.UTF_8));
            headerBase64UrlEncoded = base64Url(headerBase64UrlEncoded);
            stringBuilder.append(headerBase64UrlEncoded);
            return stringBuilder.toString();
        } catch (CodecException e) {
            throw new JwtContentException(JwtErrorCodes.CODEC, "Error when encoding the object to Json!", e);
        }
    }

    public String encodeBody(TokenData tokenData) throws JwtContentException {
        LOG.debug("Trying to encode tokendata: {}", tokenData);
        StringBuilder stringBuilder = new StringBuilder(200);
        try {
            String headerJson = jsonCodec.encode(tokenData);
            LOG.debug("As json, we have: {}", headerJson);
            String headerBase64UrlEncoded = base64Codec.encode(headerJson.getBytes(StandardCharsets.UTF_8));
            headerBase64UrlEncoded = base64Url(headerBase64UrlEncoded);
            stringBuilder.append(headerBase64UrlEncoded);
            LOG.debug("Result so far is: {}", stringBuilder);
            return stringBuilder.toString();
        } catch (CodecException e) {
            throw new JwtContentException(JwtErrorCodes.CODEC, "Error when encoding the object to Json!", e);
        }
    }

    private String base64Url(String base64String) {
        base64String = base64String.replaceAll("\n", "");
        base64String = base64String.replaceAll("=", "");
        return base64String;
    }

    public String createToken(Algorithm algorithm, String type, String contentType, TokenData tokenData, Key key) throws JwtContentException {
        String header = createHeader(algorithm, type, contentType);
        String body = encodeBody(tokenData);
        String sig = createSignatureFromBody(header, body, algorithm, key);

        StringBuilder sb = new StringBuilder(200);
        sb.append(header).append('.').append(body);
        if (sig != null) {
            sb.append('.').append(sig);
        }
        return sb.toString();
    }

    private String createSignatureFromBody(String header, String encodedBody, Algorithm algorithm, Key key) throws JwtContentException {
        String result = null;
        try {
            switch (algorithm) {
                case HMAC_SHA256:
                    Mac mac = Mac.getInstance("HmacSHA256");
                    mac.init(key);
                    mac.update(encodedBody.getBytes(StandardCharsets.UTF_8));
                    byte[] resultBytes = mac.doFinal();
                    result = base64Codec.encode(resultBytes);
                    result = base64Url(result);
                    break;
            }
        } catch (NoSuchAlgorithmException e) {
            throw new JwtContentException(JwtErrorCodes.UNKNOWN_ALGORITHM, "No idea how to handle this algorithm: " + algorithm, e);
        } catch (InvalidKeyException e) {
            throw new JwtContentException(JwtErrorCodes.INVALID_KEY, "The given key is invalid to use for signing the token!", e);
        } finally {

        }
        return result;
    }
}
