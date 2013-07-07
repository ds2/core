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
/**
 * 
 */
package ds2.oss.core.elasticsearch.api;

import java.net.InetSocketAddress;

import org.elasticsearch.client.Client;

/**
 * A contract for an ES node.
 * 
 * @author dstrauss
 * @version 0.2
 */
public interface ElasticSearchNode {
    /**
     * Returns a configured client.
     * 
     * @return a client
     */
    Client get();
    
    /**
     * Adds a list of new transport endpoints.
     * 
     * @param isa
     *            the new endpoints to add
     */
    void addTransport(InetSocketAddress... isa);
    
    /**
     * Removes a list of transports.
     * 
     * @param isa
     *            the transports to remove.
     */
    void removeTransport(InetSocketAddress... isa);

    void waitForClusterYellowState();
    void waitForClusterGreenState();
}
