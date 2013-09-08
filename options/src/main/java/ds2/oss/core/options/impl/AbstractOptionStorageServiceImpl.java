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
/**
 * 
 */
package ds2.oss.core.options.impl;

import javax.inject.Inject;

import ds2.oss.core.api.PersistenceSupport;
import ds2.oss.core.api.options.Option;
import ds2.oss.core.api.options.OptionIdentifier;
import ds2.oss.core.api.options.OptionStorageService;
import ds2.oss.core.options.api.OptionFactory;

/**
 * The option storage impl.
 * 
 * @author dstrauss
 * @version 0.3
 * @param <E>
 *            the persistence type
 */
public abstract class AbstractOptionStorageServiceImpl<E> implements OptionStorageService<E> {
    /**
     * The option factory.
     */
    @Inject
    private OptionFactory optionFac;
    
    @Override
    public <V> Option<E, V> createOption(final OptionIdentifier<V> ident, final V val) {
        final Option<E, V> option = optionFac.createOptionEntity(ident, null, val);
        getDataStore().persist(option);
        return option;
    }
    
    /**
     * Returns the datastore to use to store and load options.
     * 
     * @return the persistence support for dealing with options.
     */
    protected abstract PersistenceSupport<Option<E, ?>, E> getDataStore();
}
