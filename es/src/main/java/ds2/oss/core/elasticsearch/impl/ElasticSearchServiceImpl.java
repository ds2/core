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

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import ds2.oss.core.api.IoService;
import ds2.oss.core.elasticsearch.api.CodecProvider;
import org.elasticsearch.action.WriteConsistencyLevel;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.refresh.RefreshRequestBuilder;
import org.elasticsearch.action.admin.indices.refresh.RefreshResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.elasticsearch.api.ElasticSearchNode;
import ds2.oss.core.elasticsearch.api.ElasticSearchService;
import ds2.oss.core.elasticsearch.api.TypeCodec;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
  private CodecProvider codecProvider;
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
  public <T> T put(final String index, final T t, final TypeCodec<T> codec) {
    if (t == null) {
      throw new IllegalArgumentException("You must give a dto to put into the index!");
    }
    TypeCodec<T> typeCodec = codec;
    if (codec == null) {
      Class<T> c = (Class<T>) t.getClass();
      typeCodec = codecProvider.findFor(c);
      if (typeCodec == null) {
        throw new IllegalArgumentException("A codec is required yet!");
      }
    }
    final IndexRequestBuilder resp =
        esNode.get().prepareIndex(index, typeCodec.getIndexTypeName())
            .setSource(typeCodec.toJson(t));
    if (typeCodec.refreshOnIndexing()) {
      resp.setRefresh(true);
    }
    if (typeCodec.replicateOnIndexing()) {
      resp.setConsistencyLevel(WriteConsistencyLevel.ALL);
    }
    IndexResponse response = resp.execute().actionGet();
    LOG.debug("Response is {}", response);
    return t;
  }

  @Override
  public <T> T load(String index, Class<T> c, String id) {
    TypeCodec<T> codec = codecProvider.findFor(c);
    if (codec == null) {
      LOG.warn("No codec found for type {}! Cannot convert back into a dto therefore.", c);
      return null;
    }
    GetRequestBuilder getRequestBuilder = esNode.get().prepareGet();
    getRequestBuilder.setId(id).setIndex(index).setType(codec.getIndexTypeName());
    GetResponse result = getRequestBuilder.execute().actionGet();
    T rc = null;
    if (result.isExists()) {
      rc = codec.toDto(result.getSource());
    } else {
      LOG.debug("Could not find document with id {} in {}", new Object[]{id, index});
    }
    return rc;
  }

  @Override
  public void refreshIndexes(String... indexes) {
    RefreshRequestBuilder cmd = esNode.get().admin().indices().prepareRefresh(indexes);
    RefreshResponse result = cmd.execute().actionGet();
    if (result.getSuccessfulShards() <= 0) {
      LOG.warn("Shards could not be refreshed successfully! result is {}", result);
    }
  }

  @Override
  public void deleteIndexes(String... indexes) {
    DeleteIndexRequestBuilder deleteIndexRequestBuilder = esNode.get().admin().indices().prepareDelete(indexes);
    DeleteIndexResponse resp = deleteIndexRequestBuilder.execute().actionGet();
    if (!resp.isAcknowledged()) {
      LOG.warn("Delete is not acknowledged!");
    } else {
      LOG.debug("Deleting indexes {} done.", indexes);
    }
  }

  @Override
  public <T> List<T> getDefaultData(Class<T> c) {
    if (c == null) {
      return null;
    }
    TypeCodec<T> codec = codecProvider.findFor(c);
    if (codec == null) {
      LOG.warn("No codec found for type {}!", c);
      return null;
    }
    Package dtoPackage = c.getPackage();
    StringBuilder packageNameFilter = new StringBuilder(dtoPackage.getName().replaceAll("\\.", "/"));
    packageNameFilter.append("/").append(c.getSimpleName());
    packageNameFilter.append("-elasticsearch.insert");
    List<T> rc = new ArrayList<>();
    try {
      ImmutableSet<ClassPath.ResourceInfo> resources = ClassPath.from(getClass().getClassLoader()).getResources();
      List<ClassPath.ResourceInfo> foundInserts = filterResources(packageNameFilter.toString(), resources.asList());
      if (!foundInserts.isEmpty()) {
        for (ClassPath.ResourceInfo ri : foundInserts) {
          String res = ri.getResourceName();
          String content = io.loadResource(res);
          if (content != null) {
            T t = codec.toDto(content);
            if (t != null) {
              rc.add(t);
            }
          }
        }
      }
    } catch (IOException e) {
      LOG.debug("Error when scanning the resources in the current classpath!", e);
    }
    return rc;
  }

  private List<ClassPath.ResourceInfo> filterResources(String name, List<ClassPath.ResourceInfo> resources) {
    LOG.debug("Trying to find resources in {}", name);
    List<ClassPath.ResourceInfo> rc = new ArrayList<>();
    for (ClassPath.ResourceInfo ri : resources) {
      if (ri.getResourceName().startsWith(name)) {
        LOG.debug("Found resource {}", ri);
        rc.add(ri);
      }
    }
    return rc;
  }

  @Override
  public <T> void insertDefaultData(String index, Class<T> c) {
    //load all JSON files and its content
    //get an idea of the id of each document

  }

}
