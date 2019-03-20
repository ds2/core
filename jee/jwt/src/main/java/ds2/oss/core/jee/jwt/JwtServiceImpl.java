package ds2.oss.core.jee.jwt;

import ds2.oss.core.api.Base64Codec;
import ds2.oss.core.api.CodecException;
import ds2.oss.core.api.JsonCodec;
import ds2.oss.core.jee.jwt.dto.HeaderDto;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;

@Dependent
@Setter
public class JwtServiceImpl {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Inject
    private JsonCodec jsonCodec;
    @Inject
    private Base64Codec base64Codec;

    public String createToken(Algorithm algorithm, TokenData tokenData) throws JwtContentException {
        HeaderDto headerDto = HeaderDto.builder().alg(algorithm.getFieldValue()).build();
        StringBuilder stringBuilder = new StringBuilder(200);
        try {
            String headerJson = jsonCodec.encode(headerDto);
            stringBuilder.append(base64Codec.encode(headerJson.getBytes(StandardCharsets.UTF_8)));
            return stringBuilder.toString();
        } catch (CodecException e) {
            throw new JwtContentException(JwtErrorCodes.CODEC, "Error when encoding the object to Json!", e);
        }
    }
}
