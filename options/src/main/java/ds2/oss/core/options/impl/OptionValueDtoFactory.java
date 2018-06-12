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
package ds2.oss.core.options.impl;

import ds2.oss.core.api.dto.impl.OptionValueDto;
import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.api.options.OptionValueContext;
import ds2.oss.core.api.options.OptionValueStage;
import ds2.oss.core.options.api.OptionValueFactory;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;

/**
 * The factory to generate option value DTOs.
 *
 * @author dstrauss
 * @version 0.3
 */
@ApplicationScoped
public class OptionValueDtoFactory implements OptionValueFactory {

    /*
     * (non-Javadoc)
     * @see
     * ds2.oss.core.options.api.OptionValueFactory#createOptionValueDto(ds2.oss.core.api.options
     * .OptionIdentifier, java.lang.Object, ds2.oss.core.api.options.OptionValueContext,
     * java.lang.Object)
     */
    @Override
    public <K, V> OptionValueDto<K, V> createOptionValueDto(OptionIdentifier<V> ident, K primaryKey,
                                                            OptionValueContext ctx, V val) {
        OptionValueDto<K, V> rc = new OptionValueDto<K, V>();
        rc.setId(primaryKey);
        if (ctx != null) {
            rc.setCluster(ctx.getCluster());
            rc.setConfiguration(ctx.getConfiguration());
            rc.setServer(ctx.getServer());
            rc.setRequestedDomain(ctx.getRequestedDomain());
        }
        rc.setCreated(LocalDateTime.now());
        rc.setValidFrom(LocalDateTime.now());
        if (ident.isEncrypted()) {
            rc.setUnencryptedValue(val);
        } else {
            rc.setValue(val);
        }
        rc.setValueType(ident.getValueType());
        rc.setStage(OptionValueStage.Prepared);
        return rc;
    }

}
