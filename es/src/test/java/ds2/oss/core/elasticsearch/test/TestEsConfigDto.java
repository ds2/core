/**
 *
 */
package ds2.oss.core.elasticsearch.test;

import ds2.oss.core.elasticsearch.api.EsConfig;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author  dstrauss
 */
@ApplicationScoped
public class TestEsConfigDto implements EsConfig {

    /**
     */
    public TestEsConfigDto() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public String getClusterName() {
        return "localCluster";
    }
}
