/**
 * 
 */
package ds2.oss.core.elasticsearch.api;

import ds2.oss.core.api.IErrorData;

/**
 * @author dstrauss
 *
 */
public enum ElasticSearchErrors implements IErrorData {
    /**
     * If the put operation failed.
     */
    PutFailed(IErrorData.SERVICE_ERROR + 1);
    /**
     * the error id.
     */
    private int id;
    
    /**
     * 
     * Inits the error with the given error id.
     * 
     * @param id
     *            the error id
     */
    private ElasticSearchErrors(int id) {
        this.id = id;
    }
    
    @Override
    public int getId() {
        return id;
    }
    
}
