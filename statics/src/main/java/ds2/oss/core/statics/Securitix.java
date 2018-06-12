package ds2.oss.core.statics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by deindesign on 10.03.16.
 */
public interface Securitix {
    Logger LOG= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    Lock LOCK=new ReentrantLock();

    /**
     * Adds the given provider to the list of supported security providers.
     * @param p the provider
     */
    static void installProvider(Provider p){
        LOCK.lock();
        try {
            if(p==null){
                throw new IllegalArgumentException("No provider given!");
            }
            final Provider[] providers = Security.getProviders();
            if (providers != null) {
                final List<Provider> providerList = Arrays.asList(providers);
                if (!providerList.contains(p)) {
                    LOG.info("Adding provider {} to list", p);
                    Security.addProvider(p);
                } else {
                    LOG.info("Provider {} already registered. Nothing to do here.", p);
                }
            } else {
                LOG.info("Adding provider {}", p);
                Security.addProvider(p);
            }
        } finally {
            LOCK.unlock();
        }
    }

    /**
     * Installs the given provider at the given position.
     * @param p the provider
     * @param position the position to insert the provider at. Starts with 1.
     */
    static void installProvider(Provider p, int position){
        LOCK.lock();
        try {
            if(p==null){
                throw new IllegalArgumentException("No provider given!");
            }
            final Provider[] providers = Security.getProviders();
            if (providers != null) {
                final List<Provider> providerList = Arrays.asList(providers);
                if (!providerList.contains(p)) {
                    LOG.info("Adding provider {} to list", p);
                    Security.insertProviderAt(p, position);
                } else {
                    LOG.info("Provider {} already registered. Nothing to do here.", p);
                }
            } else {
                LOG.info("Adding provider {}", p);
                Security.insertProviderAt(p, position);
            }
        } finally {
            LOCK.unlock();
        }
    }

    /**
     * Removes the provider with the given name.
     * @param name the name of the provider.
     */
    static void removeProviderByName(String name){
        LOCK.lock();
        try {
            if(name==null){
                return;
            }
            LOG.debug("Removing provider {}", name);
            Security.removeProvider(name);
        } finally {
            LOCK.unlock();
        }

    }

    /**
     * Returns a list of currently known providers.
     * @return a list of currently known providers.
     */
    static List<String> getCurrentSecurityProviders(){
        LOCK.lock();
        try {
            List<String> rc=new ArrayList<>();
            Provider[] providers=Security.getProviders();
            if(providers!=null){
                for(Provider p : providers){
                    LOG.debug("Provider found: {}", p);
                    Set<Provider.Service> services=p.getServices();
                    LOG.debug("Services of this provider: {}", services);
                    rc.add(p.getName());
                }
            }
            return rc;
        } finally {
            LOCK.unlock();
        }
    }
}
