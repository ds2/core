/*
 * Copyright 2019 DS/2 <dstrauss@ds-2.de>
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

import java.time.LocalDateTime;
import java.util.Map;

public interface TokenData extends Map<String, Object> {
    String CLAIM_ID = "idt";
    String CLAIM_ISSUER = "iss";
    String CLAIM_SUBJECT = "sub";
    String CLAIM_AUDIENCE = "aud";
    String CLAIM_EXPIRATION_TIME = "exp";
    String CLAIM_NOT_BEFORE = "nbf";
    String CLAIM_ISSUED_AT = "iat";
    String CLAIM_JWT_ID = "jti";
    String CLAIM_JOSE_TYPE = "typ";
    String CLAIM_JOSE_CONTENTTYPE = "cty";

    String getId();

    LocalDateTime getCreated();

    LocalDateTime getValidUntil();

    String getSubject();
}
