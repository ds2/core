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

import ds2.oss.core.jee.jwt.api.TokenData;
import ds2.oss.core.statics.Converts;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedHashMap;
import java.util.List;

public class TokenDataMap extends LinkedHashMap<String, Object> implements TokenData {
    @Override
    public String getId() {
        return (String) get(CLAIM_ID);
    }

    @Override
    public String getIssuer() {
        return (String) get(CLAIM_ISSUER);
    }

    @Override
    public LocalDateTime getCreated() {
        return mapStringToLdt(get(CLAIM_ISSUED_AT));
    }

    private static LocalDateTime mapStringToLdt(Object s) {
        LocalDateTime localDateTime = null;
        if (s != null) {
            long numVal = Converts.toLong(s, 0);
            if (numVal > 0) {
                Instant epochSecondsInstant = Instant.ofEpochSecond(numVal);
                localDateTime = epochSecondsInstant.atZone(ZoneId.systemDefault()).toLocalDateTime();
            }
        }
        return localDateTime;
    }

    @Override
    public LocalDateTime getExpirationTime() {
        return mapStringToLdt(get(CLAIM_EXPIRATION_TIME));
    }

    @Override
    public LocalDateTime getNotBefore() {
        return mapStringToLdt(get(CLAIM_NOT_BEFORE));
    }

    @Override
    public String getJwtId() {
        return null;
    }
    
    @Override
    public String getSubject() {
        return (String) get(CLAIM_SUBJECT);
    }

    @Override
    public List<String> getAudience() {
        return null;
    }
}
