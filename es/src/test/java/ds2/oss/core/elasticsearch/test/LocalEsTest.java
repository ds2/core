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
package ds2.oss.core.elasticsearch.test;

import java.util.Date;
import java.util.Map;

import ds2.oss.core.elasticsearch.api.TypeCodec;
import ds2.oss.core.elasticsearch.impl.EsCodecAnnotationLiteral;
import ds2.oss.core.elasticsearch.test.dto.MyNews;
import org.elasticsearch.action.admin.cluster.state.ClusterStateResponse;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import ds2.oss.core.elasticsearch.api.ElasticSearchNode;
import ds2.oss.core.elasticsearch.api.ElasticSearchService;

/**
 * Tests for the ES service.
 * 
 * @author dstrauss
 * @version 0.2
 */
@Test(singleThreaded = true)
public class LocalEsTest extends AbstractInjectionEnvironment {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory
        .getLogger(LocalEsTest.class);

    /**
     * The test object.
     */
    private ElasticSearchService to;
    /**
     * The ES node.
     */
    private ElasticSearchNode esNode;
    /**
     * The index name to use.
     */
    private static final String indexName = "testindex1";
    /**
     * The index type name.
     */
    private final String indexType = "news";
    /**
     * The mapping.
     */
    private static final String NEWS_MAPPING =
        "{\"news\": {\n"
            + "    \"_source\": {\"enabled\": false},\n"
            + "    \"properties\": {\n"
            + "      \"title\": {\"type\": \"string\", \"index\": \"analyzed\"},\n"
            + "      \"message\": {\"type\": \"string\", \"index\": \"analyzed\"},\n"
            + "      \"postDate\": {\"type\": \"date\", \"index\": \"analyzed\"},\n"
            + "      \"author\": {\"type\": \"string\", \"index\": \"analyzed\"}\n"
            + "    }\n" + "  }\n" + "}";
    /**
     * The codec.
     */
    private NewsCodec newsCodec;
    
    /**
     * Inits the test.
     */
    public LocalEsTest() {
        // nothing special to do
    }
    

    @BeforeClass
    public void onMethod() {
        to = getInstance(ElasticSearchService.class);
        newsCodec = getInstance(NewsCodec.class);
        esNode = getInstance(ElasticSearchNode.class);
    }
    
    @Test
    public void testExistense() {
        Assert.assertNotNull(to);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testPutNull() {
        to.put(indexName, null, null);
    }
    
    @Test
    public void testPut1() {
        MyNews mn = new MyNews();
        mn.setAuthor("dstrauss");
        mn.setMsg("This is a simple test message.");
        mn.setPostDate(new Date());
        mn.setTitle("Hello, world");
        to.put(indexName, mn, newsCodec);
    }
    
    /**
     * Prepares the index.
     */
    @BeforeClass
    private void prepareIndex() {
        LOG.info("Preparing index");
        final boolean indexExists =
            esNode.get().admin().indices().prepareExists(indexName).execute()
                .actionGet().isExists();
        if (!indexExists) {
            esNode.get().admin().indices().prepareCreate(indexName).execute()
                .actionGet();
            esNode.waitForClusterYellowState();
        }
        LOG.info("Checking mappings");
        final ClusterStateResponse resp =
            esNode.get().admin().cluster().prepareState()
                .setFilterIndices(indexName).execute().actionGet();
        final Map<String, MappingMetaData> mappings =
            resp.getState().getMetaData().index(indexName).mappings();
        if (!mappings.containsKey(indexType)) {
            esNode.get().admin().indices().preparePutMapping(indexName)
                .setType(indexType).setSource(NEWS_MAPPING).execute()
                .actionGet();
        }
        LOG.info("Wait for index to come up");
        esNode.waitForClusterYellowState();
        LOG.info("Index is online. Continue with test.");
    }
    
}
