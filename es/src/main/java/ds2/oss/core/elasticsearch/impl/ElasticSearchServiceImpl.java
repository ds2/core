/*
 * Copyright 2012-2015 Dirk Strauss
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.WriteConsistencyLevel;
import org.elasticsearch.action.admin.cluster.state.ClusterStateResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.delete.DeleteMappingRequestBuilder;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.admin.indices.refresh.RefreshRequestBuilder;
import org.elasticsearch.action.admin.indices.refresh.RefreshResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.indices.IndexMissingException;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ResourceInfo;

import ds2.oss.core.api.CodecException;
import ds2.oss.core.api.IoService;
import ds2.oss.core.api.JsonCodecException;
import ds2.oss.core.elasticsearch.api.CodecProvider;
import ds2.oss.core.elasticsearch.api.ElasticSearchErrors;
import ds2.oss.core.elasticsearch.api.ElasticSearchException;
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
     * Filters some resources.
     * 
     * @param name
     *            the start of the resource name
     * @param resources
     *            the resources to scan for
     * @return the found resources that being with the name
     */
    private static List<ClassPath.ResourceInfo> filterResources(final String name,
        final List<ClassPath.ResourceInfo> resources) {
        LOG.debug("Trying to find resources in {}", name);
        final List<ClassPath.ResourceInfo> rc = new ArrayList<>();
        for (ClassPath.ResourceInfo ri : resources) {
            if (ri.getResourceName().startsWith(name)) {
                LOG.debug("Found resource {}", ri);
                rc.add(ri);
            }
        }
        return rc;
    }
    
    /**
     * This method tries to find the id of the json document. Usually, this is the first sequence
     * before the .json ending.
     * 
     * @param resourceName
     *            the json resource name
     * @return the id, or null if no id has been found
     */
    private static String findIdFromResource(final String resourceName) {
        final Pattern insertPattern = Pattern.compile("(\\.[a-zA-Z0-9-]+)?\\.json$");
        String rc = null;
        final Matcher m = insertPattern.matcher(resourceName);
        if (m.find()) {
            final String foundPattern = m.group();
            final int cutIndex = foundPattern.indexOf(".json");
            if (cutIndex > 0) {
                rc = foundPattern.substring(1, cutIndex);
            }
            
        }
        return rc;
    }
    
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ElasticSearchServiceImpl.class);
    /**
     * The ES client to use.
     */
    @Inject
    private ElasticSearchNode esNode;
    
    /**
     * Any known codecs.
     */
    @Inject
    private CodecProvider codecProvider;
    
    /**
     * The io service.
     */
    @Inject
    private IoService io;
    
    /**
     * Inits the bean.
     */
    public ElasticSearchServiceImpl() {
        // nothing special to do
    }
    
    @Override
    public void deleteIndexes(final String... indexes) {
        try {
            final DeleteIndexRequestBuilder deleteIndexRequestBuilder =
                esNode.get().admin().indices().prepareDelete(indexes);
            final DeleteIndexResponse resp = deleteIndexRequestBuilder.get();
            if (!resp.isAcknowledged()) {
                LOG.warn("Delete is not acknowledged!");
            } else {
                LOG.debug("Deleting indexes {} done.", new Object[] { indexes });
            }
        } catch (IndexMissingException e) {
            LOG.debug("Given index did not exist!", e);
            LOG.info("The index(es) {} were not existing! Ignoring delete request.", indexes);
        }
    }
    
    @Override
    public <T> T get(final String index, final Class<T> c, final String id) {
        LOG.debug("Entering get request for index {}, type {} and id {}", new Object[] { index, c, id });
        final TypeCodec<T> codec = codecProvider.findFor(c);
        if (codec == null) {
            LOG.warn("No codec found for type {}! Cannot convert back into a dto therefore.", c);
            return null;
        }
        final GetRequestBuilder getRequestBuilder = esNode.get().prepareGet();
        getRequestBuilder.setId(id).setIndex(index).setType(codec.getIndexTypeName());
        T rc = null;
        try {
            GetResponse result = getRequestBuilder.execute().actionGet();
            if (result.isExists()) {
                LOG.debug("Result from ES is {}", result);
                String json = result.getSourceAsString();
                if (json != null) {
                    LOG.debug("using json to decode: {}", json);
                    rc = codec.toDto(json);
                } else {
                    LOG.debug("Using field-based decoding of result!");
                    rc = codec.toDto(result.getSource());
                }
            } else {
                LOG.debug("Could not find document with id {} in {}", new Object[] { id, index });
            }
        } catch (ElasticsearchException e) {
            LOG.warn("Error when performing the query!", e);
        } catch (JsonCodecException e) {
            LOG.warn("Error when decoding the result source!", e);
        }
        
        LOG.debug("Result is {}", rc);
        return rc;
    }
    
    @Override
    public <T> List<T> getDefaultData(final Class<T> c) throws JsonCodecException {
        if (c == null) {
            throw new IllegalArgumentException("No class given to check for default data!");
        }
        final TypeCodec<T> codec = codecProvider.findFor(c);
        if (codec == null) {
            LOG.warn("No codec found for type {}!", c);
            return null;
        }
        final Package dtoPackage = c.getPackage();
        final StringBuilder packageNameFilter = new StringBuilder(dtoPackage.getName().replaceAll("\\.", "/"));
        packageNameFilter.append("/").append(c.getSimpleName());
        packageNameFilter.append("-elasticsearch.insert");
        final List<T> rc = new ArrayList<>();
        try {
            final ImmutableSet<ClassPath.ResourceInfo> resources =
                ClassPath.from(getClass().getClassLoader()).getResources();
            final List<ClassPath.ResourceInfo> foundInserts =
                filterResources(packageNameFilter.toString(), resources.asList());
            if (!foundInserts.isEmpty()) {
                for (ClassPath.ResourceInfo ri : foundInserts) {
                    final String res = ri.getResourceName();
                    final String content = io.loadResource(res);
                    if (content != null) {
                        final T t = codec.toDto(content);
                        if (t != null) {
                            rc.add(t);
                        }
                    }
                }
            }
        } catch (final IOException e) {
            LOG.debug("Error when scanning the resources in the current classpath!", e);
        }
        return rc;
    }
    
    @Override
    public <T> boolean insertDefaultData(final String index, final Class<T> c) {
        // load all JSON files and its content
        if (c == null) {
            throw new IllegalArgumentException("No class given to check for default data!");
        }
        final TypeCodec<T> codec = codecProvider.findFor(c);
        if (codec == null) {
            LOG.warn("No codec found for type {}!", c);
            return false;
        }
        final Package dtoPackage = c.getPackage();
        final StringBuilder packageNameFilter = new StringBuilder(dtoPackage.getName().replaceAll("\\.", "/"));
        packageNameFilter.append("/").append(c.getSimpleName());
        packageNameFilter.append("-elasticsearch.insert");
        ImmutableSet<ClassPath.ResourceInfo> resources;
        boolean rc = false;
        try {
            resources = ClassPath.from(getClass().getClassLoader()).getResources();
            final List<ClassPath.ResourceInfo> foundInserts =
                filterResources(packageNameFilter.toString(), resources.asList());
            final BulkRequestBuilder brb = esNode.get().prepareBulk();
            for (ResourceInfo ri : foundInserts) {
                final String resourceName = ri.getResourceName();
                final String content = io.loadResource(resourceName);
                if (content == null) {
                    LOG.warn("No content found for resource {}", resourceName);
                    continue;
                }
                final String id = findIdFromResource(resourceName);
                final IndexRequestBuilder irb = prepareIndexing(index, codec);
                irb.setSource(content);
                if (id != null) {
                    irb.setId(id);
                }
                brb.add(irb);
            }
            final BulkResponse bulkResult = brb.execute().actionGet();
            LOG.debug("Results: {}", new Object[] { bulkResult.getItems() });
            if (bulkResult.hasFailures()) {
                LOG.warn("Some errors occurred on insert!");
                rc = false;
            } else {
                rc = true;
            }
        } catch (final IOException e) {
            LOG.warn("Error when scanning the resources!", e);
        }
        return rc;
    }
    
    @Override
    public boolean installOrUpdateIndex(final String indexName, final Class<?>... dtoClasses) {
        final boolean rc = true;
        final boolean indexExists =
            esNode.get().admin().indices().prepareExists(indexName).execute().actionGet().isExists();
        if (!indexExists) {
            esNode.get().admin().indices().prepareCreate(indexName).execute().actionGet();
            esNode.waitForClusterYellowState();
        } else {
            LOG.info("Index {} already exists, continuing.", indexName);
        }
        LOG.info("Checking mappings");
        final ClusterStateResponse resp =
            esNode.get().admin().cluster().prepareState().setIndices(indexName).execute().actionGet();
        final ImmutableOpenMap<String, MappingMetaData> mappings =
            resp.getState().getMetaData().index(indexName).mappings();
        for (Class<?> dtoClass : dtoClasses) {
            final TypeCodec<?> codec = codecProvider.findFor(dtoClass);
            if (codec == null) {
                LOG.warn("No codec found for type {}, continuing without update.", dtoClass);
                continue;
            }
            final String indexType = codec.getIndexTypeName();
            if (mappings.containsKey(indexType)) {
                LOG.debug("Deleting mapping for type {}", indexType);
                final DeleteMappingRequestBuilder prepDelMapping =
                    esNode.get().admin().indices().prepareDeleteMapping(indexName);
                prepDelMapping.setType(indexType);
                prepDelMapping.execute().actionGet();
            }
            final PutMappingResponse result =
                esNode.get().admin().indices().preparePutMapping(indexName).setType(indexType)
                    .setSource(codec.getMapping()).execute().actionGet();
            if (!result.isAcknowledged()) {
                LOG.warn("Mapping for type {} on index {} has not been acknowlegded. Expect problems!", new Object[] {
                    indexType, indexName, });
            } else {
                LOG.debug("Installing mapping for type {} seems to be ok", indexType);
            }
        }
        LOG.info("Wait for index to come up");
        esNode.waitForClusterYellowState();
        return rc;
    }
    
    /**
     * Prepares an index operation.
     * 
     * @param index
     *            the index to use
     * @param typeCodec
     *            the type codec to use
     * @return the index request builder
     */
    private IndexRequestBuilder prepareIndexing(final String index, final TypeCodec<?> typeCodec) {
        final IndexRequestBuilder rc = esNode.get().prepareIndex(index, typeCodec.getIndexTypeName());
        if (typeCodec.refreshOnIndexing()) {
            rc.setRefresh(true);
        }
        if (typeCodec.replicateOnIndexing()) {
            rc.setConsistencyLevel(WriteConsistencyLevel.ALL);
        }
        return rc;
    }
    
    @Override
    public <T> String put(final String index, final T t, final TypeCodec<T> codec) throws CodecException,
        ElasticSearchException {
        if (t == null) {
            throw new IllegalArgumentException("You must give a dto to put into the index!");
        }
        TypeCodec<T> typeCodec = codec;
        if (codec == null) {
            final Class<T> c = (Class<T>) t.getClass();
            typeCodec = codecProvider.findFor(c);
            if (typeCodec == null) {
                throw new IllegalArgumentException("A codec is required yet!");
            }
        }
        final IndexRequestBuilder req = prepareIndexing(index, typeCodec);
        String source = typeCodec.toJson(t);
        LOG.debug("Json to store is {}", source);
        req.setSource(source);
        String id = null;
        try {
            final IndexResponse response = req.get();
            if (response.isCreated()) {
                id = response.getId();
            } else {
                LOG.debug("No creation done!");
            }
            LOG.debug("Response is {}, id will be {}", new Object[] { response, id });
        } catch (ElasticsearchException e) {
            throw new ElasticSearchException(ElasticSearchErrors.PutFailed, e);
        }
        return id;
    }
    
    @Override
    public boolean refreshIndexes(final String... indexes) {
        LOG.debug("Performing refresh on indexes {}", new Object[] { indexes });
        boolean rc = false;
        final RefreshRequestBuilder cmd = esNode.get().admin().indices().prepareRefresh(indexes);
        final RefreshResponse result = cmd.execute().actionGet();
        if (result.getSuccessfulShards() <= 0) {
            LOG.warn("Shards could not be refreshed successfully! result is {}", result);
        } else {
            LOG.debug("Done");
            rc = true;
        }
        return rc;
    }
    
    @Override
    public <T> List<T> searchAny(final String indexname, final Class<T> dtoClass) {
        final TypeCodec<T> codec = codecProvider.findFor(dtoClass);
        if (codec == null) {
            throw new IllegalArgumentException("Cannot deal with the given type! Please install codec.");
        }
        final SearchRequestBuilder searchQuery = esNode.get().prepareSearch(indexname);
        searchQuery.setQuery(QueryBuilders.matchAllQuery());
        searchQuery.setPostFilter(FilterBuilders.typeFilter(codec.getIndexTypeName()));
        searchQuery.setSize(100);
        searchQuery.setTypes(codec.getIndexTypeName());
        final SearchResponse result = searchQuery.execute().actionGet();
        List<T> rc = Collections.emptyList();
        if (!result.isTimedOut() && result.getSuccessfulShards() > 0) {
            rc = new ArrayList<>((int) result.getHits().getTotalHits());
            final SearchHits hits = result.getHits();
            LOG.debug("hits = {}", hits.getTotalHits());
            for (SearchHit hit : hits.getHits()) {
                if (hit.isSourceEmpty()) {
                    LOG.warn("Source is empty!");
                }
                T t = null;
                try {
                    t = codec.toDto(hit.getSourceAsString());
                } catch (JsonCodecException e) {
                    LOG.warn("Error when decoding a json document!", e);
                }
                if (t == null) {
                    continue;
                }
                LOG.debug("Adding object to result: {}", t);
                rc.add(t);
            }
        }
        return rc;
    }
}
