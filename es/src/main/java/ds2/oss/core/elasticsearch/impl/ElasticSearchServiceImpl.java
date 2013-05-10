/**
 * 
 */
package ds2.oss.core.elasticsearch.impl;

import java.util.Iterator;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.elasticsearch.action.admin.cluster.state.ClusterStateResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.delete.DeleteMappingRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.cluster.metadata.MappingMetaData;
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
    @Inject
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
            String typeName = tc.getIndexType();
            LOG.info("Index is now {}", index);
            IndicesExistsResponse existsResponse =
                esNode.get().admin().indices().prepareExists(index).execute()
                    .actionGet();
            if (existsResponse.isExists()) {
                LOG.info("index already exists.");
                continue;
            }
            checkMapping(index, typeName, tc.getMapping());
            /*
             * esNode.get().admin().indices().preparePutMapping(index)
             * .setType("page").setSource(xbMapping).execute().actionGet();
             */
        }
    }
    
    @Override
    public <T> T put(final T t, final TypeCodec<T> codec) {
        if (t == null) {
            return null;
        }
        if (codec == null) {
            return null;
        }
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
    
    private void checkMapping(final String index, final String type,
        final String jsonTypeMap) {
        boolean indexExists =
            esNode.get().admin().indices().prepareExists(index).execute()
                .actionGet().isExists();
        if (!indexExists) {
            LOG.info("Creating index {}", index);
            esNode.get().admin().indices().prepareCreate(index).execute()
                .actionGet();
        }
        final ClusterStateResponse resp =
            esNode.get().admin().cluster().prepareState().execute().actionGet();
        final Map<String, MappingMetaData> mappings =
            resp.getState().metaData().index(index).mappings();
        if (mappings.containsKey(type)) {/* type exists */
            LOG.info("Deleting mapping of {}", type);
            final DeleteMappingRequest delMap = new DeleteMappingRequest(index);
            delMap.type(type);
            esNode.get().admin().indices().deleteMapping(delMap).actionGet();
        }
        LOG.debug("Adding new mapping via {}", jsonTypeMap);
        esNode.get().admin().indices().preparePutMapping(index)
            .setSource(jsonTypeMap).execute().actionGet();
        LOG.debug("Wait for ES to come up");
        esNode.get().admin().cluster().prepareHealth().setWaitForYellowStatus()
            .execute().actionGet();
    }
}
