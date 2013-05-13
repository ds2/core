/**
 * 
 */
package ds2.oss.core.elasticsearch.test;

import java.util.Date;
import java.util.Map;

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
public class LocalEsTest {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory
        .getLogger(LocalEsTest.class);
    /**
     * The classpath scanner.
     */
    private static Weld weld = new Weld();
    /**
     * The container.
     */
    private static WeldContainer wc;
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
        // TODO Auto-generated constructor stub
    }
    
    @BeforeSuite
    public void onSuite() {
        wc = weld.initialize();
    }
    
    @AfterSuite
    public void onSuiteEnd() {
        weld.shutdown();
    }
    
    /**
     * Returns an instance of the given class.
     * 
     * @param c
     *            the class
     * @return an instance
     */
    public static <T> T getInstance(final Class<T> c) {
        return wc.instance().select(c).get();
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
    
    @Test
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
            waitForYellow();
        }
        LOG.info("Checking mappings");
        final ClusterStateResponse resp =
            esNode.get().admin().cluster().prepareState().execute().actionGet();
        final Map<String, MappingMetaData> mappings =
            resp.getState().metaData().index(indexName).mappings();
        if (!mappings.containsKey(indexType)) {
            esNode.get().admin().indices().preparePutMapping(indexName)
                .setSource(NEWS_MAPPING).execute().actionGet();
        }
        LOG.info("Wait for index to come up");
        waitForYellow();
        LOG.info("Index is online. Continue with test.");
    }
    
    private void waitForYellow() {
        esNode.get().admin().cluster().prepareHealth().setWaitForYellowStatus()
            .execute().actionGet();
    }
}
