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

import java.lang.invoke.MethodHandles;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;

import org.infinispan.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.InfinispanStore;
import ds2.oss.core.api.Persistable;

/**
 * The impl.
 * 
 * @author dstrauss
 * @version 0.3
 * 
 * @param <K>
 *            the key type
 * @param <V>
 *            the value type
 */
@Dependent
public class InfinispanStoreBean<K, V extends Persistable<K>> implements InfinispanStore<K, V> {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    /**
     * The cache instance.
     */
    private Cache<K, V> cache;
    
    @PostConstruct
    public void onLoad() {
        LOG.debug("I am alive");
    }
    
    /**
     * Actions to perform at stop.
     */
    @PreDestroy
    public void onStop() {
        LOG.debug("Stopping cache..");
        cache.stop();
    }
    
    @Override
    public V store(final V e) {
        if (e == null) {
            throw new IllegalArgumentException("You must give a value to store!");
        }
        if (e.getId() == null) {
            throw new IllegalArgumentException("Given persistable has no id!");
        }
        return cache.put(e.getId(), e);
    }
    
    @Override
    public V get(final K k) {
        return cache.get(k);
    }
    
    void setCache(Cache<K, V> foundCache) {
        cache = foundCache;
    }
}
