package ds2.oss.core.infinispan.impl;

import ds2.oss.core.api.InfinispanService;

import javax.enterprise.context.ApplicationScoped;

/**
 * The impl.
 */
@ApplicationScoped
public class InfinispanServiceImpl implements InfinispanService{
    @Override
    public <E> E store(E e) {
        return null;
    }
}
