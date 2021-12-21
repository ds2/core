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
package ds2.oss.core.dbtools.modules;

import ds2.oss.core.api.EditableLifeCycleAware;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * A life cycle aware db module.
 *
 * @author dstrauss
 * @version 0.3
 */
@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class LifeCycleAwareModule implements EditableLifeCycleAware {
    public static final String COL_VALIDFROM = "valid_from";
    public static final String COL_VALIDTO = "valid_to";

    /**
     * The svuid.
     */
    private static final long serialVersionUID = 6897754896689099253L;
    /**
     * Valid from.
     */
    @Column(name = COL_VALIDFROM, nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime validFrom;
    /**
     * Valid to.
     */
    @Column(name = COL_VALIDTO)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime validTo;

}
