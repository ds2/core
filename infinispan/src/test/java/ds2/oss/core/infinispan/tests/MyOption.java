/*
 * Copyright 2018 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
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
