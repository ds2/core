/*
 * Copyright 2018 DS/2 <dstrauss@ds-2.de>
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
package ds2.oss.core.hazelcast;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import ds2.oss.core.api.IdAware;
import ds2.oss.core.api.IdAwareCache;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentMap;

public abstract class HazelcastImpl<K, V extends IdAware<K>> implements IdAwareCache<K, V> {
    private ConcurrentMap<K, V> map;

    @PostConstruct
    public void onLoad() {
        Config config = new Config();
        HazelcastInstance h = Hazelcast.newHazelcastInstance(config);
        map = h.getMap("my-distributed-map");
    }

    @Override
    public V get(K k) {
        return map.get(k);
    }

    @Override
    public V store(V e) {
        return map.put(e.getId(), e);
    }
}
