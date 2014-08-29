/*
 * Copyright 2012-2013 Dirk Strauss
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
package ds2.oss.core.api.options;

import java.io.Serializable;

import ds2.oss.core.api.environment.Cluster;
import ds2.oss.core.api.environment.RuntimeConfiguration;
import ds2.oss.core.api.environment.ServerIdentifier;

/**
 * The context of an option value. These are any attributes that may change an option value request.
 *
 * @author dstrauss
 * @version 0.3
 */
public interface OptionValueContext extends Serializable {
    
    /**
     * Returns the cluster of the option value.
     *
     * @return the cluster
     */
    Cluster getCluster();
    
    /**
     * Returns the configuration this value is meant for.
     *
     * @return the runtime configuration
     */
    RuntimeConfiguration getConfiguration();
    
    /**
     * Returns the server this value is meant for. Usually, this is blank because
     * {@link #getCluster()} and {@link #getConfiguration()} are sufficient. This is if one server
     * within the same cluster must behave different. Or whatever usecase this is for.
     *
     * @return the server. Null indicates all servers, no specific one.
     */
    ServerIdentifier getServer();
    
    /**
     * Returns the domain name that is being used on the client side to access the server. This is
     * if there is a cluster which serves multiple domains, and you need domain-specific values.
     *
     * @return the requested domain name
     */
    String getRequestedDomain();
}
