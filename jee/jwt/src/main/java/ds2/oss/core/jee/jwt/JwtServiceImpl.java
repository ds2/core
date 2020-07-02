/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package ds2.oss.core.jee.jwt;

import ds2.oss.core.api.Base64Codec;
import ds2.oss.core.api.CodecException;
import ds2.oss.core.api.JsonCodec;
import ds2.oss.core.jee.jwt.api.*;
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

    public String createHeader(Algorithm algorithm, String type, String contentType) throws JwtContentException {
        LOG.debug("Will create a header with alg={}, type={} and contentType={}", algorithm, type, contentType);
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
            LOG.debug("Header is: {}", stringBuilder.toString());
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
            LOG.debug("Result for body is: {}", stringBuilder);
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

    public String createToken(String type, String contentType, TokenData tokenData, TokenSigner signer) throws JwtContentException, JwtKeyException {
        LOG.debug("Will try to create a token with alg {}, and tokenData={}", signer, tokenData);
        String header = createHeader(signer.getAlgorithm(), type, contentType);
        String body = encodeBody(tokenData);
        String sig = signer.getBase64UrlSignatureFrom(header + "." + body);

        StringBuilder sb = new StringBuilder(200);
        sb.append(header).append('.').append(body).append('.');
        if (sig != null) {
            sb.append(sig);
        }
        String resultToken = sb.toString();
        LOG.debug("Token for this run is: {}", resultToken);
        return resultToken;
    }
}
