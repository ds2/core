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
package ds2.oss.core.jee.jwt.impl;

import ds2.oss.core.jee.jwt.api.Algorithm;
import ds2.oss.core.jee.jwt.api.JwtContentException;
import ds2.oss.core.jee.jwt.api.JwtErrorCodes;
import ds2.oss.core.jee.jwt.api.JwtKeyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class HmacShaSignerImpl extends AbstractSignerImpl {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private Key secretKey;
    private Base64UrlConverter base64UrlConverter;

    public HmacShaSignerImpl(Algorithm algorithm, Key secretKey) {
        super(algorithm);
        this.secretKey = secretKey;
        base64UrlConverter = new Base64UrlConverter();
    }

    @Override
    public String getBase64UrlSignatureFrom(String headerDotBodyBase64UrlString) throws JwtContentException, JwtKeyException {
        LOG.debug("trying to sign headerBody: {}", headerDotBodyBase64UrlString);
        String result = null;
        Algorithm algorithm = getAlgorithm();
        try {
            switch (algorithm) {
                case NONE:
                    //nothing to sign here
                    break;
                case HMAC_SHA256:
                case HS384:
                case HS512:
                    if (secretKey == null) {
                        throw new JwtKeyException(JwtErrorCodes.MISSING_KEY, "No key given to use for signing!");
                    }
                    Mac mac = Mac.getInstance(algorithm.getMacName());
                    mac.init(secretKey);
                    mac.update(headerDotBodyBase64UrlString.getBytes(StandardCharsets.UTF_8));
                    byte[] resultBytes = mac.doFinal();
                    result = base64UrlConverter.toBase64Url(resultBytes);
                    break;
                default:
                    LOG.warn("Algorithm not yet implemented: {}", algorithm);
                    break;
            }
        } catch (NoSuchAlgorithmException e) {
            throw new JwtContentException(JwtErrorCodes.UNKNOWN_ALGORITHM, "No idea how to handle this algorithm: " + algorithm, e);
        } catch (InvalidKeyException e) {
            throw new JwtContentException(JwtErrorCodes.INVALID_KEY, "The given key is invalid to use for signing the token!", e);
        } finally {

        }
        LOG.debug("Signature result is: {}", result);
        return result;
    }
}
