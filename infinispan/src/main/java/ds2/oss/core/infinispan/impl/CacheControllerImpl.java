package ds2.oss.core.infinispan.impl;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Set;

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
    public static <K, V> Cache<K, V> provideCache(final String resFile, final String cName, final Class<K> kClass,
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
    public <K, V extends Persistable<K>> InfinispanStore<K, V> createInjection(final InjectionPoint p) {
        Set<Annotation> qualifiers = p.getQualifiers();
        InfinispanConfig config = findAnnotation(qualifiers, InfinispanConfig.class);
        if (config == null) {
            throw new IllegalStateException("Cannot find the infinispan config annotation at injection point " + p);
        }
        // provideCache(config.xmlFile(), config.cacheName(), null, null)
        return null;
    }
    
    @Produces
    public <K, V> Configuration loadConfig() {
        return new ConfigurationBuilder().eviction().strategy(EvictionStrategy.LRU).maxEntries(100).build();
    }
    
    /**
     * Finds a specific annotation.
     * 
     * @param qualifiers
     *            the qualifiers to scan
     * @param c
     *            the annotation class
     * @return the found annotation qualifier, or null if not found
     */
    private static <A extends Annotation> A findAnnotation(final Set<Annotation> qualifiers, final Class<A> c) {
        if (qualifiers != null) {
            for (Annotation a : qualifiers) {
                if (c.isAssignableFrom(a.getClass())) {
                    A rc = c.cast(a);
                    return rc;
                }
            }
        }
        return null;
    }
}
