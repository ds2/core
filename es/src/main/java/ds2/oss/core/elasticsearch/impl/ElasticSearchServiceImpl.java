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
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import ds2.oss.core.elasticsearch.api.CodecProvider;
import org.elasticsearch.action.WriteConsistencyLevel;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.refresh.RefreshRequest;
import org.elasticsearch.action.admin.indices.refresh.RefreshRequestBuilder;
import org.elasticsearch.action.admin.indices.refresh.RefreshResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
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
  private CodecProvider codecProvider;

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
    TypeCodec<T> typeCodec=codec;
    if (codec == null) {
      Class<T> c= (Class<T>) t.getClass();
      typeCodec = codecProvider.findFor(c);
      if(typeCodec==null){
        throw new IllegalArgumentException("A codec is required yet!");
      }
    }
    final IndexRequestBuilder resp =
        esNode.get().prepareIndex(index, typeCodec.getIndexTypeName())
            .setSource(typeCodec.toJson(t))
        //.setConsistencyLevel(WriteConsistencyLevel.ALL)
        ;
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
  public void refreshIndexes(String... indexes) {
    RefreshRequestBuilder cmd = esNode.get().admin().indices().prepareRefresh(indexes);
    RefreshResponse result = cmd.execute().actionGet();
    if (result.getSuccessfulShards() <= 0) {
      LOG.warn("Shards could not be refreshed successfully! result is {}", result);
    }
  }

  @Override
  public void deleteIndexes(String... indexes) {
    DeleteIndexRequestBuilder deleteIndexRequestBuilder=esNode.get().admin().indices().prepareDelete(indexes);
    DeleteIndexResponse resp=deleteIndexRequestBuilder.execute().actionGet();
    if(!resp.isAcknowledged()) {
      LOG.warn("Delete is not acknowledged!");
    } else {
      LOG.debug("Deleting indexes {} done.",indexes);
    }
  }

}
