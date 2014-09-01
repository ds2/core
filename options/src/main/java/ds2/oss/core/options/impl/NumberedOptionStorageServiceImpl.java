/*
 * Copyright 2012-2014 Dirk Strauss
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

import ds2.oss.core.api.dto.impl.OptionDto;
import ds2.oss.core.api.dto.impl.OptionValueDto;
import ds2.oss.core.api.options.JournalAction;
import ds2.oss.core.api.options.NumberedOptionStorageService;
import ds2.oss.core.api.options.Option;
import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.api.options.OptionServiceJournal;
import ds2.oss.core.api.options.OptionStage;
import ds2.oss.core.api.options.OptionValue;
import ds2.oss.core.api.options.OptionValueContext;
import ds2.oss.core.options.api.NumberedOptionValuePersistenceSupport;
import ds2.oss.core.options.api.NumberedOptionsPersistenceSupport;
import ds2.oss.core.options.api.OptionFactory;
import ds2.oss.core.options.api.OptionValueEncrypter;
import ds2.oss.core.options.api.OptionValueFactory;

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
    private NumberedOptionsPersistenceSupport numberedPersistenceSupport;
    /**
     * The numbered option value db.
     */
    @Inject
    private NumberedOptionValuePersistenceSupport numOptionValDb;
    /**
     * The option factory.
     */
    @Inject
    private OptionFactory optionFac;
    /**
     * The option value factory.
     */
    @Inject
    private OptionValueFactory optionValFac;
    /**
     * The journal.
     */
    @Inject
    private OptionServiceJournal journal;
    
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
        final OptionValueDto<Long, V> option = optionValFac.createOptionValueDto(optionIdent, null, ctx, value);
        numOptionValDb.persist(option);
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
        String encStr = null;
        V newVal = val;
        if (ident.isEncrypted()) {
            OptionValueEncrypter<V> encSvc = null;
            encStr = encSvc.encrypt(val);
            newVal = null;
        }
        final OptionDto<Long, V> option = optionFac.createOptionDto(ident, null, newVal);
        numberedPersistenceSupport.persist(option);
        journal.createdOption(option);
        if (ident.isEncrypted()) {
        }
        return option;
    }
    
    @Override
    public <V> Option<Long, V> setOptionStage(final OptionIdentifier<V> endpoint, final OptionStage newVal) {
        final OptionDto<Long, V> rc = numberedPersistenceSupport.setOptionStage(endpoint, newVal);
        journal.addEntry(null, JournalAction.UPDATE_OPTION_STAGE, rc.getId(), null, newVal);
        return rc;
    }
    
}
