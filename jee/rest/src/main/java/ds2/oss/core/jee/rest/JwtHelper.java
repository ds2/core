package ds2.oss.core.jee.rest;

import ds2.oss.core.api.JsonCodec;
import ds2.oss.core.api.JsonCodecException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public interface JwtHelper {
    Logger LOGGER = LoggerFactory.getLogger(JwtHelper.class);

    static Map<String, Object> parseBodyWithoutCheckingSignature(String jsonWebTokenFull, JsonCodec codec) throws JsonCodecException {
        LOGGER.debug("Got token to decode: {}", jsonWebTokenFull);
        Map<String, Object> bodyMap = new HashMap<>(1);
        String[] splitToken = jsonWebTokenFull.split("\\.");
        String unsignedToken = splitToken[0] + "." + splitToken[1] + ".";
        String subPayload = splitToken[1];
        LOGGER.debug("Payload retrieved is: {}", subPayload);
        byte[] jsonData = Base64.getDecoder().decode(subPayload);
        Object rc = codec.decode(new String(jsonData), Object.class);
        return bodyMap;
    }
}
