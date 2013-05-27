package ds2.oss.core.elasticsearch.impl;

import ds2.oss.core.elasticsearch.api.ElasticSearchNode;

import org.elasticsearch.action.admin.cluster.state.ClusterStateResponse;
import org.elasticsearch.cluster.metadata.MappingMetaData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import javax.inject.Inject;

/**
 * Some common usecases.
 */
@ApplicationScoped
public class UseCases {

    private static final Logger LOG = LoggerFactory.getLogger(UseCases.class);

    @Inject
    private ElasticSearchNode esNode;

    /**
     * Deletes a type from the index.
     *
     * @param  index  the index name
     * @param  type   the type name
     */
    public void deleteEntriesOfType(final String index, final String type) {
        esNode.get().prepareDelete().setIndex(index).setType(type).execute().actionGet();
        esNode.waitForClusterYellowState();
    }

    /**
     * Creates an index.
     *
     * @param   indexName  the index name
     *
     * @return  TRUE if index has been created, otherwise FALSE
     */
    public boolean createIndex(final String indexName) {
        final boolean indexExists = esNode.get().admin().indices().prepareExists(indexName).execute().actionGet()
            .isExists();
        if (!indexExists) {
            esNode.get().admin().indices().prepareCreate(indexName).execute().actionGet();
            esNode.waitForClusterYellowState();
            return true;
        }
        return false;
    }

    /**
     * Adds a mapping for a given type.
     *
     * @param  indexName  the index name
     * @param  typeName   the type name
     * @param  json       the json containing the mapping data
     */
    public void addMapping(final String indexName, final String typeName, final String json) {
        LOG.info("Checking mappings");
        final ClusterStateResponse resp = esNode.get().admin().cluster().prepareState().setFilterIndices(indexName)
            .execute().actionGet();
        final Map<String, MappingMetaData> mappings = resp.getState().getMetaData().index(indexName).mappings();
        if (!mappings.containsKey(typeName)) {
            esNode.get().admin().indices().preparePutMapping(indexName).setType(typeName).setSource(json).execute()
                .actionGet();
        }
        esNode.waitForClusterYellowState();
    }
}
