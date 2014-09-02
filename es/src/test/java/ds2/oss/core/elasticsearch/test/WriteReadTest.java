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
package ds2.oss.core.elasticsearch.test;

import java.util.Date;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ds2.oss.core.elasticsearch.api.ElasticSearchNode;
import ds2.oss.core.elasticsearch.api.ElasticSearchService;
import ds2.oss.core.elasticsearch.api.TypeCodec;
import ds2.oss.core.elasticsearch.impl.UseCases;
import ds2.oss.core.elasticsearch.test.dto.CountryDto;
import ds2.oss.core.elasticsearch.test.dto.MyNews;
import ds2.oss.core.elasticsearch.test.support.CountryCodec;
import ds2.oss.core.testutils.AbstractInjectionEnvironment;

/**
 * Check write read behaviour.
 * 
 * @author dstrauss
 * @version 0.2
 */
public class WriteReadTest extends AbstractInjectionEnvironment {
    /**
     * The index name to use.
     */
    private static final String INDEXNAME = "rwtest";
    /**
     * The ES service.
     */
    private ElasticSearchService esSvc;
    /**
     * Some use cases.
     */
    private UseCases uc;
    /**
     * The ES node.
     */
    private ElasticSearchNode esNode;
    /**
     * The news codec.
     */
    private NewsCodec newsCodec;
    /**
     * The country codec.
     */
    private TypeCodec<CountryDto> countryCodec;
    
    @BeforeClass
    public void onClass() {
        esSvc = getInstance(ElasticSearchService.class);
        uc = getInstance(UseCases.class);
        uc.createIndex(INDEXNAME);
        newsCodec = getInstance(NewsCodec.class);
        countryCodec = getInstance(CountryCodec.class);
        esNode = getInstance(ElasticSearchNode.class);
        uc.addMapping(INDEXNAME, newsCodec.getIndexTypeName(), newsCodec.getMapping());
    }
    
    @Test
    public void rwTest1() {
        final MyNews news1 = new MyNews();
        news1.setAuthor("baumkuchen");
        news1.setMsg("This is a very sensitive message.");
        news1.setPostDate(new Date());
        news1.setTitle("Secrets beyond imagination");
        esSvc.put(INDEXNAME, news1, newsCodec);
        final SearchRequestBuilder searchQuery =
            esNode.get().prepareSearch(INDEXNAME).setTypes(newsCodec.getIndexTypeName())
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(QueryBuilders.matchAllQuery())
                .setFilter(FilterBuilders.termFilter("author", "baumkuchen"));
        final SearchResponse resp = searchQuery.execute().actionGet();
        final long count = resp.getHits().totalHits();
        Assert.assertEquals(count, 1);
    }
    
    @Test
    public void rwTest2() {
        final CountryDto c = new CountryDto();
        c.setIsoCode("DE");
        c.setName("Germany");
        esSvc.put(INDEXNAME, c, countryCodec);
        final SearchRequestBuilder searchQuery =
            esNode.get().prepareSearch(INDEXNAME).setTypes(countryCodec.getIndexTypeName())
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(QueryBuilders.matchAllQuery())
                .setFilter(FilterBuilders.termFilter("name", "germany"));
        SearchResponse resp = searchQuery.execute().actionGet();
        long count = resp.getHits().totalHits();
        // is async -> will not be available NOW
        Assert.assertEquals(count, 0);
        esSvc.refreshIndexes(INDEXNAME);
        resp = searchQuery.execute().actionGet();
        count = resp.getHits().totalHits();
        Assert.assertEquals(count, 1);
    }
    
    @AfterClass
    public void onEndClass() {
        esSvc.deleteIndexes(INDEXNAME);
    }
}
