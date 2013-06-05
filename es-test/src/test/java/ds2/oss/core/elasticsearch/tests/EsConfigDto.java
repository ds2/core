package ds2.oss.core.elasticsearch.tests;

import ds2.oss.core.elasticsearch.api.EsConfig;

import javax.enterprise.context.ApplicationScoped;

/**
 * Dummy dto.
 */
@ApplicationScoped
public class EsConfigDto implements EsConfig {

    @Override
    public String getClusterName() {
        return "dummyCluster1";
    }
}
