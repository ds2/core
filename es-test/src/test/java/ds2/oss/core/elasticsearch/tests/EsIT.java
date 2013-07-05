package ds2.oss.core.elasticsearch.tests;

import org.testng.annotations.BeforeClass;

import ds2.oss.core.elasticsearch.api.ElasticSearchNode;

/**
 * The integration test.
 * 
 * @author dstrauss
 * @version 0.2
 */
public class EsIT extends AbstractInjectionEnvironment {
    /**
     * The node.
     */
    private ElasticSearchNode esNode;
    
    @BeforeClass
    public void onClass() {
        esNode = getInstance(ElasticSearchNode.class);
    }
    
}
