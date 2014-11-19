/*
 * Copyright 2012-2014 Dirk Strauss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ds2.oss.core.elasticsearch.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.elasticsearch.action.admin.cluster.state.ClusterStateResponse;
import org.elasticsearch.action.deletebyquery.DeleteByQueryResponse;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.elasticsearch.api.ElasticSearchNode;

/**
 * Some common usecases.
 * 
 * @version 0.2
 * @author dstrauss
 */
@ApplicationScoped
public class UseCases {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(UseCases.class);
    
    /**
     * The ES node.
     */
    @Inject
    private ElasticSearchNode esNode;
    
    /**
     * Adds a mapping for a given type.
     * 
     * @param indexName
     *            the index name
     * @param typeName
     *            the type name
     * @param json
     *            the json containing the mapping data
     */
    public void addMapping(final String indexName, final String typeName, final String json) {
        LOG.info("Checking mappings");
        final ClusterStateResponse resp =
            esNode.get().admin().cluster().prepareState().setIndices(indexName).execute().actionGet();
        final ImmutableOpenMap<String, MappingMetaData> mappings =
            resp.getState().getMetaData().index(indexName).mappings();
        if (!mappings.containsKey(typeName)) {
            esNode.get().admin().indices().preparePutMapping(indexName).setType(typeName).setSource(json).execute()
                .actionGet();
        }
        esNode.waitForClusterYellowState();
    }
    
    /**
     * Creates an index.
     * 
     * @param indexName
     *            the index name
     * 
     * @return TRUE if index has been created, otherwise FALSE
     */
    public boolean createIndex(final String indexName) {
        final boolean indexExists =
            esNode.get().admin().indices().prepareExists(indexName).execute().actionGet().isExists();
        if (!indexExists) {
            esNode.get().admin().indices().prepareCreate(indexName).execute().actionGet();
            esNode.waitForClusterYellowState();
            return true;
        }
        LOG.warn("index {} already exists, cannot be created again!", indexName);
        return false;
    }
    
    /**
     * Deletes a type from the index.
     * 
     * @param index
     *            the index name
     * @param type
     *            the type name
     */
    public void deleteEntriesOfType(final String index, final String type) {
        final DeleteByQueryResponse response =
            esNode.get().prepareDeleteByQuery(index).setQuery(QueryBuilders.termQuery("_type", type)).execute()
                .actionGet();
        LOG.info("Result: {}", response);
        esNode.waitForClusterYellowState();
    }
}
