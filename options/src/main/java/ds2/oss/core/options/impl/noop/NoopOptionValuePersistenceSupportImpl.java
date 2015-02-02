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
package ds2.oss.core.options.impl.noop;

import java.lang.invoke.MethodHandles;

import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.dto.impl.OptionValueDto;
import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.api.options.OptionValue;
import ds2.oss.core.api.options.OptionValueContext;
import ds2.oss.core.api.options.OptionValueStage;
import ds2.oss.core.options.api.NumberedOptionValuePersistenceSupport;

/**
 * Simple noop option value persistence support.
 * 
 * @author dstrauss
 * @version 0.3
 */
@ApplicationScoped
public class NoopOptionValuePersistenceSupportImpl implements NumberedOptionValuePersistenceSupport {
    /**
     * a logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.PersistenceSupport#persist(ds2.oss.core.api.Persistable)
     */
    @Override
    public void persist(OptionValueDto<Long, ?> t) {
        LOG.info("Using non-operational method. Please reconfigure injection!");
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.PersistenceSupport#getById(java.lang.Object)
     */
    @Override
    public OptionValueDto<Long, ?> getById(Long e) {
        LOG.info("Using non-operational method. Please reconfigure injection!");
        return null;
    }

    @Override
    public void setStage(Long id, OptionValueStage newStage) {
        LOG.info("Using non-operational method! Please reconfigure injection!");
    }

    @Override
    public <V> OptionValue<Long, V> findBestOptionValue(OptionIdentifier<V> ident, OptionValueContext ctx) {
        LOG.info("Using non-operational method! Please reconfigure injection!");
        return null;
    }
    
}
