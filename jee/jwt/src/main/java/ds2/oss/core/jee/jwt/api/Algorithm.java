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
package ds2.oss.core.jee.jwt.api;

import lombok.Getter;

/**
 * See RFC7518.
 */
@Getter
public enum Algorithm {
    NONE("none", null),
    HMAC_SHA256("HS256", "HmacSHA256"),
    HS384("HS384", "HmacSHA384"),
    HS512("HS512", "HmacSHA512"),
    RS256("RS256", null),
    RS384("RS384", null),
    RS512("RS512", null),
    ES256("ES256", null),
    ES384("ES384", null),
    ES512("ES512", null),
    PS256("PS256", null),
    PS384("PS384", null),
    PS512("PS512", null),

    ;
    private String fieldValue;
    private String macName;

    Algorithm(String s, String m) {
        fieldValue = s;
        macName = m;
    }
}
