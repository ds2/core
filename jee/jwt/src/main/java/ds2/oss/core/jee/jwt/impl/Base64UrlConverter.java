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

import java.util.Base64;

public class Base64UrlConverter {
    private Base64.Encoder encoder;
    private Base64.Decoder decoder;
    private Base64.Encoder urlEncoder;

    public Base64UrlConverter() {
        encoder = Base64.getEncoder();
        urlEncoder = Base64.getUrlEncoder();
        decoder = Base64.getDecoder();
    }

    public String toBase64(byte[] bytes) {
        return encoder.encodeToString(bytes);
    }

    public String toBase64Url(byte[] bytes) {
        return urlEncoder.encodeToString(bytes).replaceAll("=", "");
    }

    public byte[] decodeFromBase64(String base64String) {
        return decoder.decode(base64String);
    }
}
