package ds2.oss.core.elasticsearch.tests;

import javax.enterprise.context.ApplicationScoped;

import ds2.oss.core.elasticsearch.api.EsConfig;

/**
 * Dummy dto.
 * 
 * @version 0.2
 * @author dstrauss
 */
@ApplicationScoped
public class EsConfigDto implements EsConfig {
    
    @Override
    public String getClusterName() {
        return "dummyCluster1";
    }
}
