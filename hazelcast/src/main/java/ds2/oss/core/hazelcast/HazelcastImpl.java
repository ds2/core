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
        config.set
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
