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
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ds2.oss.core.api.CodecException;
import ds2.oss.core.api.CoreException;
import ds2.oss.core.elasticsearch.api.ElasticSearchException;
import ds2.oss.core.elasticsearch.api.ElasticSearchNode;
import ds2.oss.core.elasticsearch.api.ElasticSearchService;
import ds2.oss.core.elasticsearch.test.dto.CountryDto;
import ds2.oss.core.elasticsearch.test.dto.MyNews;
import ds2.oss.core.testutils.AbstractInjectionEnvironment;

/**
 * Tests for the ES service.
 * 
 * @author dstrauss
 * @version 0.2
 */
@Test(singleThreaded = true, groups = "locales")
public class LocalEsTest extends AbstractInjectionEnvironment {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(LocalEsTest.class);
    
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
    private static final String indexName = "localesindex";
    /**
     * The index type name.
     */
    private final String indexType = "news";
    /**
     * The codec.
     */
    private NewsCodec newsCodec;
    
    @AfterClass
    public void onEndClass() {
        to.deleteIndexes(indexName);
    }
    
    /**
     * Actions to perform on class start.
     */
    @BeforeMethod(alwaysRun = true)
    public void onMethod() {
        to = getInstance(ElasticSearchService.class);
        
    }
    
    /**
     * Prepares the index.
     */
    @BeforeClass(alwaysRun = true)
    private void prepareIndex() {
        LOG.info("Preparing index");
        to = getInstance(ElasticSearchService.class);
        to.installOrUpdateIndex(indexName, MyNews.class, CountryDto.class);
        LOG.info("Index is online. Continue with test.");
    }
    
    @Test
    public void testExistense() {
        Assert.assertNotNull(to);
    }
    
    /**
     * Test for the get method.
     * 
     * @throws CoreException
     */
    @Test(groups = "load")
    public void testGet() throws CoreException {
        Date postDate = new Date();
        final MyNews mn = new MyNews();
        mn.setAuthor("testuser");
        mn.setMsg("Hello, world, again");
        mn.setPostDate(postDate);
        mn.setTitle("Hello, again");
        final String id = to.put(indexName, mn, null);
        Assert.assertNotNull(id);
        final MyNews m2 = to.get(indexName, MyNews.class, id);
        Assert.assertNotNull(m2, "News could not be found!");
        Assert.assertNotNull(m2.getPostDate());
        Assert.assertEquals(m2.getPostDate(), postDate);
    }
    
    /**
     * Test to see if the prep data loading works as expected.
     */
    @Test(groups = "load")
    public void testLoadPreparationData() {
        Assert.assertTrue(to.insertDefaultData(indexName, MyNews.class));
        Assert.assertTrue(to.refreshIndexes(indexName));
        final MyNews n = to.get(indexName, MyNews.class, "N1");
        Assert.assertNotNull(n, "No news found!");
        // Assert.assertNotNull(n.getPostDate());
    }
    
    @Test
    public void testPut1() throws ElasticSearchException, CodecException {
        final MyNews mn = new MyNews();
        mn.setAuthor("dstrauss");
        mn.setMsg("This is a simple test message.");
        mn.setPostDate(new Date());
        mn.setTitle("Hello, world");
        to.put(indexName, mn, newsCodec);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testPutNull() throws ElasticSearchException, CodecException {
        to.put(indexName, null, null);
    }
    
    @Test
    public void testPutWithoutCodec() throws ElasticSearchException, CodecException {
        final MyNews mn = new MyNews();
        mn.setAuthor("dstrauss");
        mn.setMsg("This is a simple test message.");
        mn.setPostDate(new Date());
        mn.setTitle("Hello, world 2");
        to.put(indexName, mn, null);
    }
    
    /**
     * Test to check if some resources for a given dto class can be found.
     * 
     * @throws CoreException
     */
    @Test(groups = "scan")
    public void testScanResources() throws CoreException {
        final List<MyNews> rc = to.getDefaultData(MyNews.class);
        Assert.assertNotNull(rc);
    }
    
    /**
     * Test for the get method.
     * 
     * @throws ElasticSearchException
     * @throws CodecException
     */
    @Test(groups = "search")
    public void testSearch1() throws ElasticSearchException, CodecException {
        final MyNews mn = new MyNews();
        mn.setAuthor("testuser");
        mn.setMsg("Hello, world, again");
        mn.setPostDate(new Date());
        mn.setTitle("Hello, again");
        final String id = to.put(indexName, mn, null);
        Assert.assertNotNull(id);
        final List<MyNews> anyNews = to.searchAny(indexName, MyNews.class);
        Assert.assertNotNull(anyNews, "News could not be found!");
        Assert.assertFalse(anyNews.isEmpty());
    }
}
