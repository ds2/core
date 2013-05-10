/**
 * 
 */
package ds2.oss.core.elasticsearch.impl;

import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.elasticsearch.api.ElasticSearchNode;
import ds2.oss.core.elasticsearch.api.ElasticSearchService;
import ds2.oss.core.elasticsearch.api.TypeCodec;
import ds2.oss.core.elasticsearch.api.TypeMapping;

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
    private Instance<TypeCodec<?>> anyCodecs;
    
    /**
     * Inits the bean.
     */
    public ElasticSearchServiceImpl() {
        // TODO Auto-generated constructor stub
    }
    
    @PostConstruct
    public void onStartup() {
        LOG.info("Check indices and mappings...");
        Iterator<TypeCodec<?>> tcI = anyCodecs.iterator();
        while (tcI.hasNext()) {
            TypeCodec<?> tc = tcI.next();
            String index = tc.getIndex();
            esNode.get().admin().indices()
                .create(new CreateIndexRequest(index)).actionGet();
            /*
             * esNode.get().admin().indices().preparePutMapping(index)
             * .setType("page").setSource(xbMapping).execute().actionGet();
             */
        }
    }
    
    @Override
    public <T> T put(final T t, final TypeCodec<T> codec) {
        final IndexResponse response =
            esNode
                .get()
                .prepareIndex(
                    t.getClass().getAnnotation(TypeMapping.class).useIndex(),
                    t.getClass().getAnnotation(TypeMapping.class).value())
                .setSource(codec.toJsonMap(t)).execute().actionGet();
        LOG.debug("Response is {}", response);
        return t;
    }
}
