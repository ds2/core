package ds2.oss.core.api.environment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Simple dto.
 * 
 * @author dstrauss
 * @version 0.3
 */
@XmlType(name = "ClusterType")
public class ClusterDto implements Cluster {
    /**
     * The regex pattern for getting the cluster name.
     */
    public static final Pattern NAME_PATTERN = Pattern.compile("[A-Za-z]");
    /**
     * the name of the cluster.
     */
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
    @XmlElement
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
