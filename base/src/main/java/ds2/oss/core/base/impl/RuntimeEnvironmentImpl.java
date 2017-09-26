package ds2.oss.core.base.impl;

import ds2.oss.core.api.annotations.StringLoader;
import ds2.oss.core.api.environment.*;
import ds2.oss.core.statics.Methods;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RuntimeEnvironmentImpl implements RuntimeEnvironment {
    @Inject
    private ServerIdentifierProvider identifierProvider;
    private RuntimeType runtimeType;
    private Cluster cluster;
    @StringLoader(sysProp = "ds2CoreClusterId", envProp = "DS2_OSSCORE_CLUSTER_ID")
    @Inject
    private String clusterName;
    @StringLoader(sysProp = "runtimeEnvironment", envProp = "DS2_OSSCORE_RUNTIME_NAME")
    @Inject
    private String runtimeConfigurationName;

    @PostConstruct
    public void onLoad() {
        if (Methods.isBlank(clusterName)) {
            cluster = new ClusterDto();
        } else {
            cluster = new ClusterDto(clusterName);
        }
        runtimeType = RuntimeType.parseConfig(runtimeConfigurationName);
        if (runtimeType == null) {
            runtimeType = RuntimeType.LocalDevelopment;
        }
    }

    @Override
    public Cluster getCluster() {
        return cluster;
    }

    @Override
    public RuntimeType getRuntimeType() {
        return runtimeType;
    }

    @Override
    public ServerIdentifier getServerIdentifier() {
        return identifierProvider.getCurrentServerDetails();
    }
}
