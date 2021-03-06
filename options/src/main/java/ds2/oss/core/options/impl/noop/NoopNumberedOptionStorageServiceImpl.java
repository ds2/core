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
package ds2.oss.core.options.impl.noop;

import java.lang.invoke.MethodHandles;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.options.NumberedOptionStorageService;
import ds2.oss.core.api.options.Option;
import ds2.oss.core.api.options.OptionException;
import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.api.options.OptionStage;
import ds2.oss.core.api.options.OptionValue;
import ds2.oss.core.api.options.OptionValueContext;
import ds2.oss.core.api.options.OptionValueStage;

/**
 * A default noop storage service.
 * 
 * @author dstrauss
 * @version 0.3
 *
 */
@ApplicationScoped
public class NoopNumberedOptionStorageServiceImpl implements NumberedOptionStorageService {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    /**
     * Actions to perform at startup.
     */
    @PostConstruct
    public void onClass() {
        LOG.info("Using the non-operational NumberedOptionStorageService! Please choose an alternative.");
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.options.OptionStorageService#getOptionByIdentifier(ds2.oss.core.api.options
     * .OptionIdentifier)
     */
    @Override
    public <V> Option<Long, V> getOptionByIdentifier(OptionIdentifier<V> ident) {
        LOG.info("Using noop operation. Returning dummy value!");
        return null;
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.options.OptionStorageService#createOptionValue(ds2.oss.core.api.options. OptionIdentifier,
     * ds2.oss.core.api.options.OptionValueContext, java.util.Date, java.lang.Object)
     */
    @Override
    public <V> OptionValue<Long, V> createOptionValue(OptionIdentifier<V> optionIdent, OptionValueContext ctx,
        Date scheduleDate, V value) {
        LOG.info("Using noop operation. Returning dummy value!");
        return null;
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.options.OptionStorageService#createOption(ds2.oss.core.api.options. OptionIdentifier,
     * java.lang.Object)
     */
    @Override
    public <V> Option<Long, V> createOption(OptionIdentifier<V> ident, V val) {
        LOG.info("Using noop operation. Returning dummy value!");
        return null;
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.options.OptionStorageService#findBestOptionValueByContext(ds2.oss.core.api
     * .options.OptionIdentifier, ds2.oss.core.api.options.OptionValueContext)
     */
    @Override
    public <V> OptionValue<Long, V> findBestOptionValueByContext(OptionIdentifier<V> ident, OptionValueContext ctx) {
        LOG.info("Using noop operation. Returning dummy value!");
        return null;
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.options.OptionStorageService#getAllOptions(java.lang.String)
     */
    @Override
    public List<Option<Long, ?>> getAllOptions(final String appName) {
        LOG.info("Using noop operation. Returning dummy value!");
        return null;
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.options.OptionStorageService#setOptionStage(ds2.oss.core.api.options. OptionIdentifier,
     * ds2.oss.core.api.options.OptionStage)
     */
    @Override
    public <V> Option<Long, V> setOptionStage(OptionIdentifier<V> endpoint, OptionStage deleted) {
        LOG.info("Using noop operation. Returning dummy value!");
        return null;
    }
    
    @Override
    public <V> OptionValue<Long, V> getOptionValueById(Long id) throws OptionException {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
                                                                       // Tools | Templates.
    }
    
    @Override
    public void setOptionValueStage(Long id, OptionValueStage newStage) {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
                                                                       // Tools | Templates.
    }
    
    @Override
    public void approveOptionValue(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
                                                                       // Tools | Templates.
    }
    
}
