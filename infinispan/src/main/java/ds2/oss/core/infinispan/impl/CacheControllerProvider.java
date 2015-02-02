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
package ds2.oss.core.infinispan.impl;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.inject.Provider;

import org.infinispan.Cache;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.eviction.EvictionStrategy;
import org.infinispan.manager.DefaultCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.InfinispanStore;
import ds2.oss.core.api.Persistable;
import ds2.oss.core.api.cache.InfinispanConfig;

/**
 * Created by dstrauss on 20.08.13.
 */
@ApplicationScoped
public class CacheControllerProvider {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(CacheControllerProvider.class);
    
    /**
     * Loads a cache for the given coordinates.
     *
     * @param resFile
     * @param cName
     * @param kClass
     * @param vClass
     * @return the cache, or null
     */
    private static <K, V> Cache<K, V> provideCache(final String resFile, final String cName, final Class<K> kClass,
        final Class<V> vClass) {
        LOG.debug("Loading cache {} from resource {}", new Object[] { cName, resFile });
        Cache<K, V> rc = null;
        try {
            DefaultCacheManager cm = new DefaultCacheManager(resFile);
            rc = cm.getCache(cName);
        } catch (IOException e) {
            LOG.error("Error when loading cache config!", e);
        }
        return rc;
    }

    @Inject
    @Any
    private Provider<InfinispanStoreBean<?, ?>> stores;
    
    /**
     * Dummy generator for any @{link InfinispanStore}.
     */
    private <K, V extends Persistable<K>> InfinispanStore<K, V> createInjection(final InjectionPoint p) {
        LOG.debug("Checking cut point..");
        InfinispanConfig config = p.getAnnotated().getAnnotation(InfinispanConfig.class);
        if (config == null) {
            throw new IllegalStateException("Cannot find the infinispan config annotation at injection point " + p);
        }
        LOG.debug("Loading cache from given config data");
        Cache<K, V> foundCache = provideCache(config.xmlFile(), config.cacheName(), null, null);
        LOG.debug("Getting new instance of store bean");
        @SuppressWarnings("unchecked")
        InfinispanStoreBean<K, V> rc = (InfinispanStoreBean<K, V>) stores.get();
        rc.setCache(foundCache);
        LOG.debug("Done, returning new impl {}", rc);
        return rc;
    }
    
    /**
     * Dummy injector for any long based InfinispanStores.
     * 
     * @param p
     *            the injection point
     * @param <V>
     *            any long based persistable
     * @return the store to use
     */
    @Produces
    @InfinispanConfig
    @Dependent
    @Any
    public <V extends Persistable<Long>> InfinispanStore<Long, V> createLongInjection(final InjectionPoint p) {
        return createInjection(p);
    }
    
    /**
     * Dummy injector for any string based InfinispanStores.
     * 
     * @param p
     *            the injection point
     * @param <V>
     *            any string based persistable
     * @return the store to use
     */
    @Produces
    @InfinispanConfig
    @Dependent
    public <V extends Persistable<String>> InfinispanStore<String, V> createStringInjection(final InjectionPoint p) {
        return createInjection(p);
    }
    
    @Produces
    public <K, V> Configuration loadConfig() {
        return new ConfigurationBuilder().eviction().strategy(EvictionStrategy.LRU).maxEntries(100).build();
    }
}
