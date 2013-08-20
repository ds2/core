package ds2.oss.core.infinispan.impl;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by dstrauss on 20.08.13.
 */
public class CacheControllerImpl {
  private static final Logger LOG= LoggerFactory.getLogger(CacheControllerImpl.class);
  public <K,V> Cache<K,V> provideCache(String resFile, String cName, Class<K> kClass, Class<V> vClass){
    LOG.debug("Loading cache {} from resource {}", new Object[]{cName,resFile});
    Cache<K,V> rc=null;
    try {
      DefaultCacheManager cm=new DefaultCacheManager(resFile);
      rc=cm.getCache(cName);
    } catch (IOException e) {
      LOG.error("Error when loading cache config!",e);
    }
    return rc;
  }
}
