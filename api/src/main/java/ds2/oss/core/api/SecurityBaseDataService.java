package ds2.oss.core.api;

/**
 * A service to read a salt value and a IV from a specific file location, and provides this information
 * to the application server.
 * @author dstrauss
 * @version 0.3
 */
public interface SecurityBaseDataService extends SecurityBaseData {
  void createData();
}
