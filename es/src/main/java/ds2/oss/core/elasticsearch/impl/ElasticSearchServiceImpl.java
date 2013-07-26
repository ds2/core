/*
 * Copyright 2012-2013 Dirk Strauss
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
/**
 *
 */
package ds2.oss.core.elasticsearch.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.elasticsearch.action.WriteConsistencyLevel;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.refresh.RefreshRequestBuilder;
import org.elasticsearch.action.admin.indices.refresh.RefreshResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ResourceInfo;

import ds2.oss.core.api.IoService;
import ds2.oss.core.elasticsearch.api.CodecProvider;
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
    
    /**
     * Actions to perform at startup.
     */
    @PostConstruct
    public void onStartup() {
        LOG.info("Check indices and mappings...");
    }
    
    @Override
    public <T> String put(final String index, final T t, final TypeCodec<T> codec) {
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
        final IndexRequestBuilder resp = prepareIndexing(index, typeCodec);
        resp.setSource(typeCodec.toJson(t));
        final IndexResponse response = resp.execute().actionGet();
        final String id = response.getId();
        LOG.debug("Response is {}", response);
        return id;
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
    public <T> T get(final String index, final Class<T> c, final String id) {
        final TypeCodec<T> codec = codecProvider.findFor(c);
        if (codec == null) {
            LOG.warn("No codec found for type {}! Cannot convert back into a dto therefore.", c);
            return null;
        }
        final GetRequestBuilder getRequestBuilder = esNode.get().prepareGet();
        getRequestBuilder.setId(id).setIndex(index).setType(codec.getIndexTypeName());
        final GetResponse result = getRequestBuilder.execute().actionGet();
        T rc = null;
        if (result.isExists()) {
            LOG.debug("Result from ES is {}", result);
            result.getSource();
            rc = codec.toDto(result.getSourceAsString());
        } else {
            LOG.debug("Could not find document with id {} in {}", new Object[] { id, index });
        }
        LOG.debug("Result is {}", rc);
        return rc;
    }
    
    @Override
    public boolean refreshIndexes(final String... indexes) {
        LOG.debug("Performing refresh on indexes {}", new Object[] { indexes });
        boolean rc = false;
        final RefreshRequestBuilder cmd = esNode.get().admin().indices().prepareRefresh(indexes);
        cmd.setWaitForOperations(true);
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
    public void deleteIndexes(final String... indexes) {
        final DeleteIndexRequestBuilder deleteIndexRequestBuilder =
            esNode.get().admin().indices().prepareDelete(indexes);
        final DeleteIndexResponse resp = deleteIndexRequestBuilder.execute().actionGet();
        if (!resp.isAcknowledged()) {
            LOG.warn("Delete is not acknowledged!");
        } else {
            LOG.debug("Deleting indexes {} done.", new Object[] { indexes });
        }
    }
    
    @Override
    public <T> List<T> getDefaultData(final Class<T> c) {
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
}
