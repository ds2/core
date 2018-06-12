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

import ds2.oss.core.api.CoreErrors;
import ds2.oss.core.api.crypto.EncodedContent;
import ds2.oss.core.api.crypto.IvEncodedContent;
import ds2.oss.core.api.dto.impl.OptionDto;
import ds2.oss.core.api.dto.impl.OptionValueDto;
import ds2.oss.core.api.options.*;
import ds2.oss.core.options.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
    /**
     * The encryption provider.
     */
    @Inject
    private OptionValueEncrypterProvider encProvider;

    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.options.OptionStorageService#getOptionByIdentifier(ds2.oss.core.api.options
     * .OptionIdentifier)
     */
    @Override
    public <V> Option<Long, V> getOptionByIdentifier(final OptionIdentifier<V> ident) throws OptionException {
        final OptionDto<Long, V> foundOption = numberedPersistenceSupport.findOptionByIdentifier(ident);
        if (foundOption != null) {
            if (foundOption.isEncrypted()) {
                final OptionValueEncrypter<V> ove = encProvider.getForValueType(ident.getValueType());
                if (ove == null) {
                    throw new OptionException(CoreErrors.NoEncryptionForType);
                }
                final V val = ove.decrypt(foundOption);
                foundOption.setDecryptedValue(val);
            }
        }

        LOG.debug("Found this option: {}", foundOption);
        return foundOption;
    }

    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.options.OptionStorageService#createOptionValue(ds2.oss.core.api.options. OptionIdentifier,
     * ds2.oss.core.api.options.OptionValueContext, java.util.Date, java.lang.Object)
     */
    @Override
    public <V> OptionValue<Long, V> createOptionValue(final OptionIdentifier<V> optionIdent,
                                                      final OptionValueContext ctx, final Date scheduleDate, final V value) throws CreateOptionValueException {
        final OptionValueDto<Long, V> optionValue = optionValFac.createOptionValueDto(optionIdent, null, ctx, value);
        optionValue.setCreatedBy("test");
        if (optionIdent.isEncrypted()) {
            final OptionValueEncrypter<V> ove = encProvider.getForValueType(optionIdent.getValueType());
            if (ove == null) {
                throw new CreateOptionValueException(CoreErrors.NoEncryptionForType);
            }
            final EncodedContent encryptedContent = ove.encrypt(value);
            if (encryptedContent == null) {
                throw new CreateOptionValueException(CoreErrors.EncryptionFailed);
            }
            optionValue.setEncoded(encryptedContent.getEncoded());
            if (encryptedContent instanceof IvEncodedContent) {
                final IvEncodedContent encIvEncodedContent = (IvEncodedContent) encryptedContent;
                optionValue.setInitVector(encIvEncodedContent.getInitVector());
            }
            optionValue.setUnencryptedValue(value);
            optionValue.setValue(null);
            optionValue.setEncrypted(true);
        }
        try {
            final Option<Long, V> foundOption = getOptionByIdentifier(optionIdent);
            if (foundOption == null) {
                throw new OptionException(CoreErrors.OptionNotFound);
            }
            optionValue.setOptionReference(foundOption.getId());
        } catch (final OptionException e) {
            throw new CreateOptionValueException(CoreErrors.OptionNotFound, e);
        }
        LOG.debug("Trying to persist option value {}", optionValue);
        numOptionValDb.persist(optionValue);
        return optionValue;
    }

    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.options.OptionStorageService#findBestOptionValueByContext(ds2.oss.core.api
     * .options.OptionIdentifier, ds2.oss.core.api.options.OptionValueContext)
     */
    @Override
    public <V> OptionValue<Long, V> findBestOptionValueByContext(final OptionIdentifier<V> ident,
                                                                 final OptionValueContext ctx) {
        OptionDto<Long, V> foundOption = numberedPersistenceSupport.findOptionByIdentifier(ident);
        if (foundOption == null) {
            return null;
        }
        return numOptionValDb.findBestOptionValue(ident, ctx);
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
    public <V> Option<Long, V> createOption(final OptionIdentifier<V> ident, final V val) throws CreateOptionException {
        V newVal = val;
        if (ident.isEncrypted()) {
            newVal = null;
        }
        final OptionDto<Long, V> option = optionFac.createOptionDto(ident, null, newVal);
        option.setCreatedBy("test");
        if (ident.isEncrypted()) {
            final OptionValueEncrypter<V> ove = encProvider.getForValueType(ident.getValueType());
            if (ove == null) {
                throw new CreateOptionException(CoreErrors.NoEncryptionForType);
            }
            final EncodedContent encryptedContent = ove.encrypt(val);
            if (encryptedContent == null) {
                throw new CreateOptionException(CoreErrors.EncryptionFailed);
            }
            option.setEncoded(encryptedContent.getEncoded());
            if (encryptedContent instanceof IvEncodedContent) {
                final IvEncodedContent encIvEncodedContent = (IvEncodedContent) encryptedContent;
                option.setInitVector(encIvEncodedContent.getInitVector());
            }
            option.setDecryptedValue(val);
        }
        numberedPersistenceSupport.persist(option);
        journal.createdOption(option);
        return option;
    }

    @Override
    public <V> Option<Long, V> setOptionStage(final OptionIdentifier<V> endpoint, final OptionStage newVal) {
        final OptionDto<Long, V> rc = numberedPersistenceSupport.setOptionStage(endpoint, newVal);
        journal.addEntry(null, JournalAction.UPDATE_OPTION_STAGE, rc.getId(), null, newVal);
        return rc;
    }

    @Override
    public <V> OptionValue<Long, V> getOptionValueById(Long id) throws OptionException {
        @SuppressWarnings("unchecked")
        OptionValueDto<Long, V> foundVal = (OptionValueDto<Long, V>) numOptionValDb.getById(id);
        return foundVal;
    }

    @Override
    public void setOptionValueStage(Long id, OptionValueStage newStage) {
        numOptionValDb.setStage(id, newStage);
    }

    @Override
    public void approveOptionValue(Long id) {
        OptionValueStage newStage = OptionValueStage.Approved;
        OptionValueDto<Long, ?> foundOptionVal = numOptionValDb.getById(id);
        LocalDateTime now = LocalDateTime.now();
        if (foundOptionVal.getValidFrom().isBefore(now)) {
            newStage = OptionValueStage.Live;
        }
        if (foundOptionVal.getValidTo() != null) {
            if (now.isAfter(foundOptionVal.getValidTo())) {
                newStage = OptionValueStage.Expired;
            }
        }

        setOptionValueStage(id, newStage);
    }

}
