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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Simple dto.
 * 
 * @author dstrauss
 * @version 0.3
 */
@XmlType(name = "ClusterType")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClusterDto implements Cluster {
    /**
     * The regex pattern for getting the cluster name.
     */
    public static final Pattern NAME_PATTERN = Pattern.compile("[A-Za-z]");
    /**
     * the name of the cluster.
     */
    @XmlAttribute(name = "name")
    private char clusterName;
    
    /**
     * Inits an empty cluster dto.
     */
    public ClusterDto() {
        // nothing special to do
    }
    
    /**
     * Constructs the cluster name from the given string.
     * 
     * @param clusterEnvStr
     *            the string to analyse. The first letter is taken to be the cluster config name.
     */
    public ClusterDto(final String clusterEnvStr) {
        this();
        clusterName = parseFromString(clusterEnvStr);
    }
    
    /**
     * Inits the dto from the given values.
     * 
     * @param cluster
     *            the cluster to copy
     */
    public ClusterDto(final Cluster cluster) {
        this();
        clusterName = cluster.getClusterName();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public final synchronized char getClusterName() {
        return clusterName;
    }
    
    /**
     * Parses the given string for containing a cluster name.
     * 
     * @param s
     *            the possible name of the cluster
     * @return the cluster character name
     */
    private static char parseFromString(final String s) {
        final String clusterNamePlain = s;
        if (clusterNamePlain == null) {
            throw new IllegalArgumentException("No cluster name given!");
        }
        final Matcher m = NAME_PATTERN.matcher(clusterNamePlain);
        if (!m.find()) {
            throw new IllegalArgumentException("No valid cluster name found!");
        }
        final String g = m.group();
        final char rc = g.charAt(0);
        return rc;
    }
    
    /**
     * Sets the cluster name.
     * 
     * @param cc
     *            the clusterName to set
     */
    public final synchronized void setClusterName(final char cc) {
        clusterName = cc;
    }
    
    @Override
    public final String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(ClusterDto.class.getName());
        sb.append("(");
        sb.append("cluster=").append(clusterName);
        sb.append(")");
        return sb.toString();
    }
}
