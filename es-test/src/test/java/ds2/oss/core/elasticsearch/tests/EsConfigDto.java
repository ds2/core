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
package ds2.oss.core.elasticsearch.tests;

import java.util.Properties;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

import ds2.oss.core.elasticsearch.api.EsConfig;
import org.elasticsearch.common.transport.TransportAddress;

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
    
    @Override
    public Properties getProperties() {
        return null;
    }

    @Override
    public Set<TransportAddress> getTransportAddresses() {
        return null;
    }
}
