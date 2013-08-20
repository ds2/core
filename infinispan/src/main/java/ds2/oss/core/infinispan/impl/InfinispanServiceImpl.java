package ds2.oss.core.infinispan.impl;

import ds2.oss.core.api.InfinispanService;
import ds2.oss.core.api.es.Cacheable;
import org.infinispan.manager.EmbeddedCacheManager;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;

/**
 * The impl.
 */
public class InfinispanServiceImpl implements InfinispanService{
  private EmbeddedCacheManager embeddedCacheManager;

  public InfinispanServiceImpl(EmbeddedCacheManager ecm){
    embeddedCacheManager=ecm;
    embeddedCacheManager.getCache();
  }
  @PreDestroy
  public void onStop(){
    embeddedCacheManager.stop();
  }

  @Override
  public <E extends Cacheable> E store(E e) {
    return null;
  }

  @Override
  public <E extends Cacheable> E get(String k, Class<E> c) {
    return null;
  }
}
