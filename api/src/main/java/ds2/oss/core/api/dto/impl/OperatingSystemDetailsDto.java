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
package ds2.oss.core.api.dto.impl;

import ds2.oss.core.api.OperatingSystemDetails;
import ds2.oss.core.api.OperatingSystemNames;
import ds2.oss.core.api.Version;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by dstrauss on 21.03.17.
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class OperatingSystemDetailsDto implements OperatingSystemDetails {
    private OperatingSystemNames systemName;
    private Version version;
    private int bitSize;

    @Override
    public int compareTo(OperatingSystemDetails o) {
        int rc = systemName.compareTo(o.getSystemName());
        if (rc == 0) {
            rc = version.compareTo(o.getVersion());
        }
        if (rc == 0) {
            rc = bitSize - o.getBitSize();
        }
        return rc;
    }
}
