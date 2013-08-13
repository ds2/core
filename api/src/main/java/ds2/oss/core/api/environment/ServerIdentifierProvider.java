package ds2.oss.core.api.environment;

/**
 * A provider for getting the current server context.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface ServerIdentifierProvider {
    /**
     * Returns the details of the current server.
     * 
     * @return the server details, or null if an error occurred.
     */
    ServerIdentifier getCurrentServerDetails();
}
