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
package ds2.oss.core.api.environment;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.List;

/**
 * Interface contract to identify a single computer in a network.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface ServerIdentifier extends Serializable {
    /**
     * Returns all known ip addresses of this server.
     * 
     * @return a list of all known ip addresses
     */
    List<InetAddress> getAllKnownAddresses();
    
    /**
     * Returns the possible domain this computer belongs to.
     * 
     * @return the domain name, if available. Otherwise null.
     */
    String getDomain();
    
    /**
     * Returns the hostname of this server.
     * 
     * @return the name of this server
     */
    String getHostName();
    
    /**
     * Returns the possible remote address of this server. Usually, this will return the internal ip
     * address to be used by configuration clients or similar.
     * 
     * @return the possible remote address.
     */
    String getIpAddress();
}
