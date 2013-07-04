package ds2.oss.core.elasticsearch.tests;

import ds2.oss.core.elasticsearch.api.ElasticSearchNode;
import org.testng.annotations.BeforeClass;

/**
 * The integration test.
 *
 * @author dstrauss
 */
public class EsIT extends AbstractInjectionEnvironment{

  private ElasticSearchNode esNode;

  @BeforeClass
  public void onClass() {
    esNode=getInstance(ElasticSearchNode.class);
  }

}
