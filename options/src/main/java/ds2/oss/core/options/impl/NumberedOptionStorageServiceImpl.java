/*
 * Copyright 2012-2013 Dirk Strauss
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

import java.lang.invoke.MethodHandles;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.options.NumberedOptionStorageService;
import ds2.oss.core.api.options.Option;
import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.api.options.OptionStage;
import ds2.oss.core.api.options.OptionValue;
import ds2.oss.core.api.options.OptionValueContext;
import ds2.oss.core.options.api.NumberedOptionsPersistenceSupport;
import ds2.oss.core.options.api.OptionFactory;
import ds2.oss.core.options.api.OptionsPersistenceSupport;
import ds2.oss.core.options.api.OptionsPersistenceSupport.StorageType;
import ds2.oss.core.options.impl.dto.OptionDto;

/**
 * The implementation for numbered options.
 * 
 * @author dstrauss
 * @version 0.3
 */
@Alternative
@ApplicationScoped
public class NumberedOptionStorageServiceImpl extends AbstractOptionStorageServiceImpl<Long>
    implements
    NumberedOptionStorageService {
    /**
     * A logger.
     */
    private static final transient Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    /**
     * The persistence support to use for options..
     */
    @Inject
    @OptionsPersistenceSupport(type = StorageType.NUMBERED)
    private NumberedOptionsPersistenceSupport numberedPersistenceSupport;
    /**
     * The option factory.
     */
    @Inject
    private OptionFactory optionFac;
    
    /*
     * (non-Javadoc)
     * @see
     * ds2.oss.core.api.options.OptionStorageService#getOptionByIdentifier(ds2.oss.core.api.options
     * .OptionIdentifier)
     */
    @Override
    public <V> Option<Long, V> getOptionByIdentifier(final OptionIdentifier<V> ident) {
        final OptionDto<Long, V> foundOption = numberedPersistenceSupport.findOptionByIdentifier(ident);
        LOG.debug("Found this option: {}", foundOption);
        return foundOption;
    }
    
    /*
     * (non-Javadoc)
     * @see
     * ds2.oss.core.api.options.OptionStorageService#createOptionValue(ds2.oss.core.api.options.
     * OptionIdentifier, ds2.oss.core.api.options.OptionValueContext, java.util.Date,
     * java.lang.Object)
     */
    @Override
    public <V> OptionValue<Long, V> createOptionValue(final OptionIdentifier<V> optionIdent,
        final OptionValueContext ctx, final Date scheduleDate, final V value) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * @see
     * ds2.oss.core.api.options.OptionStorageService#findBestOptionValueByContext(ds2.oss.core.api
     * .options.OptionIdentifier, ds2.oss.core.api.options.OptionValueContext)
     */
    @Override
    public <V> OptionValue<Long, V> findBestOptionValueByContext(final OptionIdentifier<V> ident,
        final OptionValueContext ctx) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.options.OptionStorageService#getAllOptions(java.lang.String)
     */
    @Override
    public List<Option<Long, ?>> getAllOptions(final String appName) {
        return null;
    }
    
    @Override
    public <V> Option<Long, V> createOption(final OptionIdentifier<V> ident, final V val) {
        final OptionDto<Long, V> option = optionFac.createOptionDto(ident, null, val);
        numberedPersistenceSupport.persist(option);
        return option;
    }
    
    @Override
    public <V> Option<Long, V> setOptionStage(final OptionIdentifier<V> endpoint, final OptionStage deleted) {
        return numberedPersistenceSupport.setOptionStage(endpoint, deleted);
    }
    
}
