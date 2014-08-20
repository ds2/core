package ds2.oss.core.infinispan.impl;

import java.io.IOException;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.infinispan.Cache;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.eviction.EvictionStrategy;
import org.infinispan.manager.DefaultCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.InfinispanStore;
import ds2.oss.core.api.Persistable;
import ds2.oss.core.api.es.InfinispanConfig;

/**
 * Created by dstrauss on 20.08.13.
 */
@Dependent
public class CacheControllerImpl {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(CacheControllerImpl.class);
    
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
    
    @Produces
    @Dependent
    @InfinispanConfig
    @Any
    public <K, V extends Persistable<K>> InfinispanStore<K, V> createInjection(final InjectionPoint p) {
        LOG.debug("Checking cut point..");
        InfinispanConfig config = p.getAnnotated().getAnnotation(InfinispanConfig.class);
        if (config == null) {
            throw new IllegalStateException("Cannot find the infinispan config annotation at injection point " + p);
        }
        Cache<K, V> foundCache =
            provideCache(config.xmlFile(), config.cacheName(), null,
                null);
        InfinispanStoreImpl<K, V> rc = new InfinispanStoreImpl<K, V>();
        rc.setCache(foundCache);
        return rc;
    }
    
    @Produces
    public <K, V> Configuration loadConfig() {
        return new ConfigurationBuilder().eviction().strategy(EvictionStrategy.LRU).maxEntries(100).build();
    }
}
