/**
 * 
 */
package ds2.oss.core.options.api;

/**
 * The numbered options persistence support.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface NumberedOptionsPersistenceSupport extends AdditionalOptionsPersistenceSupport<Long> {
    // nothing special yet
    /**
     * The query name for an option identifier search.
     */
    String QUERY_FINDOPTIONBYIDENTIFIER = "ds2Core_findOptionByIdentifier";
    
}
