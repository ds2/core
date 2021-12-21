/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package ds2.oss.core.base.impl;

import ds2.oss.core.api.annotations.StringLoader;
import ds2.oss.core.api.environment.*;
import ds2.oss.core.statics.Methods;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.annotation.PostConstruct;

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
