package ds2.oss.core.infinispan.impl;

import java.io.IOException;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
}
