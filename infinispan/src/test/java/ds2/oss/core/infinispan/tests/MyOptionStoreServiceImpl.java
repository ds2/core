/**
 * 
 */
package ds2.oss.core.infinispan.tests;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import ds2.oss.core.api.InfinispanStore;
import ds2.oss.core.api.es.InfinispanConfig;

/**
 * @author dstrauss
 *
 */
@ApplicationScoped
public class MyOptionStoreServiceImpl {
    @Inject
    @InfinispanConfig(
        cacheName = "options",
        xmlFile = "test-infinispan.xml")
    private InfinispanStore<String, MyOption> storage;
    
    @PostConstruct
    public void onInit() {
        if (storage == null) {
            throw new IllegalStateException("Storage has not been initialized successfully!");
        }
    }
    
    public void store(MyOption opt) {
        storage.store(opt);
    }
    
    public MyOption get(String k) {
        return storage.get(k);
    }
}
