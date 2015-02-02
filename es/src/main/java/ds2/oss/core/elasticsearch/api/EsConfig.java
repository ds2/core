/*
 * Copyright 2012-2015 Dirk Strauss
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
package ds2.oss.core.elasticsearch.api;

import java.util.Properties;
import java.util.Set;

import org.elasticsearch.common.transport.TransportAddress;

/**
 * Alternate way to provide a config to the ES node.
 * 
 * @version 0.2
 * @author dstrauss
 */
public interface EsConfig {
    /**
     * Returns the cluster name.
     * 
     * @return the cluster name
     */
    String getClusterName();
    
    /**
     * This method may return additional configuration parameters for ES. Default is null.
     * 
     * @return any additional properties, or null by default.
     */
    Properties getProperties();
    
    /**
     * Returns the transport addresses to connect to.
     * 
     * @return the transport addresses
     */
    Set<TransportAddress> getTransportAddresses();
}
