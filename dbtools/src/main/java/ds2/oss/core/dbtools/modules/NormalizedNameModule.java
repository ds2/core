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

import ds2.oss.core.api.EditableNormalizedNameAware;
import ds2.oss.core.api.settable.NormalizedNameAware;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author deindesign
 */
@Embeddable
public class NormalizedNameModule implements EditableNormalizedNameAware {

    private static final long serialVersionUID = -5009052399914767060L;

    @Column(name = "norm_name", nullable = false, unique = true)
    private String normalizedName;

    @Override
    public String getNormalizedName() {
        return normalizedName;
    }

    @Override
    public void setNormalizedName(String normalizedName) {
        this.normalizedName = normalizedName;
    }

}
