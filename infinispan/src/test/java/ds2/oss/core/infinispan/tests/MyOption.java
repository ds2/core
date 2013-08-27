/**
 * 
 */
package ds2.oss.core.infinispan.tests;

import ds2.oss.core.api.Persistable;

/**
 * @author dstrauss
 * 
 */
public class MyOption implements Persistable<String> {
    /**
     * The svuid.
     */
    private static final long serialVersionUID = -5411096639085804279L;
    private String key;
    private String val;
    
    @Override
    public String getId() {
        return key;
    }
    
    /**
     * @return the val
     */
    public String getVal() {
        return val;
    }
    
    /**
     * @param val
     *            the val to set
     */
    public void setVal(final String val) {
        this.val = val;
    }
    
    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }
    
    /**
     * @param key
     *            the key to set
     */
    public void setKey(final String key) {
        this.key = key;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((key == null) ? 0 : key.hashCode());
        return result;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof MyOption)) {
            return false;
        }
        MyOption other = (MyOption) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        if (val == null) {
            if (other.val != null) {
                return false;
            }
        } else if (!val.equals(other.val)) {
            return false;
        }
        return true;
    }
    
}
