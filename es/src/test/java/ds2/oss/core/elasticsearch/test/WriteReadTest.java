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
package ds2.oss.core.elasticsearch.test;

import ds2.core.testonly.utils.AbstractInjectionEnvironment;
import ds2.oss.core.api.CodecException;
import ds2.oss.core.elasticsearch.api.ElasticSearchException;
import ds2.oss.core.elasticsearch.api.ElasticSearchNode;
import ds2.oss.core.elasticsearch.api.ElasticSearchService;
import ds2.oss.core.elasticsearch.api.TypeCodec;
import ds2.oss.core.elasticsearch.impl.UseCases;
import ds2.oss.core.elasticsearch.impl.literals.EsCodecAnnotationLiteral;
import ds2.oss.core.elasticsearch.test.dto.CountryDto;
import ds2.oss.core.elasticsearch.test.dto.MyNews;
import ds2.oss.core.elasticsearch.test.support.CountryCodec;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * Check write read behaviour.
 *
 * @author dstrauss
 * @version 0.2
 */
public class WriteReadTest extends AbstractInjectionEnvironment {
    private static final Logger LOG = LoggerFactory.getLogger(WriteReadTest.class);
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
    private TypeCodec<MyNews> newsCodec;
    /**
     * The country codec.
     */
    private TypeCodec<CountryDto> countryCodec;

    @BeforeClass
    public void onClass() {
        esSvc = getInstance(ElasticSearchService.class);
        uc = getInstance(UseCases.class);
        uc.createIndex(INDEXNAME);
        newsCodec = getInstance(NewsCodec.class, new EsCodecAnnotationLiteral(MyNews.class));
        countryCodec = getInstance(CountryCodec.class, new EsCodecAnnotationLiteral(CountryDto.class));
        esNode = getInstance(ElasticSearchNode.class);
        uc.addMapping(INDEXNAME, newsCodec.getIndexTypeName(), newsCodec.getMapping());
    }

    @AfterClass
    public void onEndClass() {
        esSvc.deleteIndexes(INDEXNAME);
    }

    @Test
    public void rwTest1() throws ElasticSearchException, CodecException {
        final MyNews news1 = new MyNews();
        news1.setAuthor("baumkuchen");
        news1.setMsg("This is a very sensitive message.");
        news1.setPostDate(new Date());
        news1.setTitle("Secrets beyond imagination");
        String id = esSvc.put(INDEXNAME, news1, newsCodec);
        esSvc.refreshIndexes(INDEXNAME);
        Assert.assertNotNull(esSvc.get(INDEXNAME, MyNews.class, id));
        final SearchRequestBuilder searchQuery =
                esNode.get().prepareSearch(INDEXNAME).setTypes(newsCodec.getIndexTypeName())
                        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(QueryBuilders.matchAllQuery())
                        .setPostFilter(FilterBuilders.termFilter("author", "baumkuchen"));
        LOG.debug("Query will be {}", searchQuery);
        final SearchResponse resp = searchQuery.get();
        final long count = resp.getHits().totalHits();
        Assert.assertEquals(count, 1);
    }

    @Test
    public void rwTest2() throws ElasticSearchException, CodecException {
        final CountryDto c = new CountryDto();
        c.setIsoCode("DE");
        c.setName("Germany");
        esSvc.put(INDEXNAME, c, countryCodec);
        final SearchRequestBuilder searchQuery =
                esNode.get().prepareSearch(INDEXNAME).setTypes(countryCodec.getIndexTypeName())
                        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(QueryBuilders.matchAllQuery())
                        .setPostFilter(FilterBuilders.termFilter("name", "germany"));
        SearchResponse resp = searchQuery.execute().actionGet();
        long count = resp.getHits().totalHits();
        // is async -> will not be available NOW
        Assert.assertEquals(count, 0);
        esSvc.refreshIndexes(INDEXNAME);
        resp = searchQuery.get();
        count = resp.getHits().totalHits();
        Assert.assertEquals(count, 1);
    }
}
