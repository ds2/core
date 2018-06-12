/*
 * Copyright 2012-2015 Dirk Strauss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ds2.oss.core.dbtools.modules;

import ds2.oss.core.api.EditableNormalizedNamedDescriptiveAware;
import ds2.oss.core.api.settable.NormalizedNamedDescriptiveAware;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author deindesign
 */
@Embeddable
public class NamedNormalizedDescriptiveModule implements EditableNormalizedNamedDescriptiveAware {

    private static final long serialVersionUID = -884204081077605434L;

    private NamedNormalizedModule nnm = new NamedNormalizedModule();
    @Column(name = "description")
    private String description;

    @Override
    public String getName() {
        return nnm.getName();
    }

    @Override
    public String getNormalizedName() {
        return nnm.getNormalizedName();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setName(String s) {
        nnm.setName(s);
    }

    @Override
    public void setNormalizedName(String s) {
        nnm.setNormalizedName(s);
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

}
