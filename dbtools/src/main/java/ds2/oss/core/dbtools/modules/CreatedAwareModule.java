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

import ds2.oss.core.api.EditableCreatedAware;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author dstrauss
 */
@Embeddable
@Getter
@Setter
@ToString
public class CreatedAwareModule implements EditableCreatedAware {
    /**
     * The svuid.
     */
    private static final long serialVersionUID = -6249059771176243495L;
    public static final String CREATED = "created";
    /**
     * The creation date.
     */
    @Column(name = CREATED, nullable = false, updatable = false)
    private LocalDateTime created = LocalDateTime.now();

    /**
     * Inits this object.
     */
    public CreatedAwareModule() {
        created = LocalDateTime.now();
    }

}
