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

import ds2.oss.core.api.settable.NormalizedNamedAware;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

/**
 *
 * @author deindesign
 */
@Embeddable
public class NamedNormalizedModule implements NormalizedNamedAware {

    private static final long serialVersionUID = -8048468907853527416L;
    @Embedded
    private NamedAwareModule nam = new NamedAwareModule();
    @Embedded
    private NormalizedNameModule nnm = new NormalizedNameModule();

    @Override
    public String getName() {
        return nam.getName();
    }

    @Override
    public String getNormalizedName() {
        return nnm.getNormalizedName();
    }

    @Override
    public void setName(String s) {
        nam.setName(s);
    }

    @Override
    public void setNormalizedName(String s) {
        nnm.setNormalizedName(s);
    }

}
