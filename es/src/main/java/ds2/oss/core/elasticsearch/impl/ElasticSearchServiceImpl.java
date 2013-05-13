/**
 * 
 */
package ds2.oss.core.elasticsearch.impl;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.elasticsearch.action.index.IndexResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.elasticsearch.api.ElasticSearchNode;
import ds2.oss.core.elasticsearch.api.ElasticSearchService;
import ds2.oss.core.elasticsearch.api.TypeCodec;

/**
 * The implementation for the ES service.
 * 
 * @author dstrauss
 * @version 0.2
 */
@ApplicationScoped
public class ElasticSearchServiceImpl implements ElasticSearchService {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory
        .getLogger(ElasticSearchServiceImpl.class);
    /**
     * The ES client to use.
     */
    @Inject
    private ElasticSearchNode esNode;
    /**
     * Any known codecs.
     */
    @Inject
    private Instance<TypeCodec<?>> anyCodecs;
    
    /**
     * Inits the bean.
     */
    public ElasticSearchServiceImpl() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Actions to perform at startup.
     */
    @PostConstruct
    public void onStartup() {
        LOG.info("Check indices and mappings...");
    }
    
    @Override
    public <T> T put(final String index, final T t, final TypeCodec<T> codec) {
        if (t == null) {
            return null;
        }
        if (codec == null) {
            return null;
        }
        final IndexResponse response =
            esNode.get().prepareIndex(index, codec.getIndexType())
                .setSource(codec.toJson(t)).execute().actionGet();
        LOG.debug("Response is {}", response);
        return t;
    }
    
}
