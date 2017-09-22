package ds2.oss.core.base.impl;

import ds2.oss.core.api.annotations.PropertiesLoader;
import ds2.oss.core.api.environment.*;
import ds2.oss.core.statics.Methods;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RuntimeEnvironmentImpl implements RuntimeEnvironment {
    @Inject
    private ServerIdentifierProvider identifierProvider;
    private RuntimeConfiguration runtimeConfiguration;
    private Cluster cluster;
    @PropertiesLoader(sysProp = "ds2CoreClusterId", envProp = "DS2_OSSCORE_CLUSTER_ID")
    @Inject
    private String clusterName;
    @PropertiesLoader(sysProp = "runtimeEnvironment", envProp = "DS2_OSSCORE_RUNTIME_NAME")
    @Inject
    private String runtimeConfigurationName;

    @PostConstruct
    public void onLoad() {
        if (Methods.isBlank(clusterName)) {
            cluster = new ClusterDto();
        } else {
            cluster = new ClusterDto(clusterName);
        }
        runtimeConfiguration = RuntimeConfiguration.parseConfig(runtimeConfigurationName);
        if (runtimeConfiguration == null) {
            runtimeConfiguration = RuntimeConfiguration.LocalDevelopment;
        }
    }

    @Override
    public Cluster getCluster() {
        return cluster;
    }

    @Override
    public RuntimeConfiguration getRuntimeConfiguration() {
        return runtimeConfiguration;
    }

    @Override
    public ServerIdentifier getServerIdentifier() {
        return identifierProvider.getCurrentServerDetails();
    }
}
