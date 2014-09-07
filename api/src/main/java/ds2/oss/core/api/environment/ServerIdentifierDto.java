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
package ds2.oss.core.api.environment;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * A dto for any known server details.
 * 
 * @author dstrauss
 * @version 0.3
 */
@XmlType(name = "serverIdentifierType")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServerIdentifierDto implements ServerIdentifier {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 1L;
    /**
     * The inet addresses of the server.
     */
    private List<InetAddress> addresses;
    /**
     * The ip address.
     */
    private String ipAddress;
    /**
     * The hostname of the server.
     */
    private String hostName;
    /**
     * A possible domain name of the server.
     */
    private String domain;
    
    /**
     * Inits the dto.
     */
    public ServerIdentifierDto() {
        addresses = new ArrayList<>();
    }
    
    @Override
    public final List<InetAddress> getAllKnownAddresses() {
        return addresses;
    }
    
    @Override
    public final String getDomain() {
        return domain;
    }
    
    @Override
    public final String getHostName() {
        return hostName;
    }
    
    /**
     * Sets the ip address.
     * 
     * @param ip
     *            ip address
     */
    public final void setIpAddress(final String ip) {
        ipAddress = ip;
    }
    
    /**
     * Sets the host name.
     * 
     * @param host
     *            the host name
     */
    public void setHostName(final String host) {
        hostName = host;
    }
    
    /**
     * Sets the domain name.
     * 
     * @param dom
     *            the domain name
     */
    public void setDomain(final String dom) {
        domain = dom;
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.foundation.environment.api.ServerIdentifier#getIpAddress()
     */
    @Override
    public String getIpAddress() {
        return ipAddress;
    }
    
    /**
     * Adds all addresses given to the currently known addresses.
     * 
     * @param addresses2
     *            the addresses to add
     */
    public void addAddresses(final Collection<InetAddress> addresses2) {
        addresses.addAll(addresses2);
    }
    
    /**
     * Adds a single new address.
     * 
     * @param ia2
     *            the address to add
     */
    public void addAddress(final InetAddress ia2) {
        addresses.add(ia2);
    }
    
    public boolean isEmpty() {
        return domain == null && hostName == null && ipAddress == null;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((addresses == null) ? 0 : addresses.hashCode());
        result = prime * result + ((domain == null) ? 0 : domain.hashCode());
        result = prime * result + ((hostName == null) ? 0 : hostName.hashCode());
        result = prime * result + ((ipAddress == null) ? 0 : ipAddress.hashCode());
        return result;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ServerIdentifierDto other = (ServerIdentifierDto) obj;
        if (addresses == null) {
            if (other.addresses != null)
                return false;
        } else if (!addresses.equals(other.addresses))
            return false;
        if (domain == null) {
            if (other.domain != null)
                return false;
        } else if (!domain.equals(other.domain))
            return false;
        if (hostName == null) {
            if (other.hostName != null)
                return false;
        } else if (!hostName.equals(other.hostName))
            return false;
        if (ipAddress == null) {
            if (other.ipAddress != null)
                return false;
        } else if (!ipAddress.equals(other.ipAddress))
            return false;
        return true;
    }
    
}
