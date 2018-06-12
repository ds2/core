package ds2.oss.core.api;

import java.util.Map;

/**
 * Created by dstrauss on 23.03.17.
 */
public interface MetadataAware<K, V> {
    Map<K, V> getMetadata();
}
