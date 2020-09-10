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

import java.time.LocalDateTime;
import java.util.List;

public interface TokenDataSetter extends CommonJwtData {
    void setId(String id);

    void setIssuer(String issuer);

    void setCreated(LocalDateTime created);

    void setExpirationTime(LocalDateTime expirationTime);

    void setNotBefore(LocalDateTime notBefore);

    void setJwtId(String jwtId);

    void setSubject(String subject);

    void setAudience(List<String> audience);
}
