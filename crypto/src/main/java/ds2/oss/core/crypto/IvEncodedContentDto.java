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
package ds2.oss.core.crypto;

import java.util.Arrays;

import ds2.oss.core.api.crypto.IvEncodedContent;

/**
 * The Iv based encoded content.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class IvEncodedContentDto extends EncodedContentDto implements IvEncodedContent {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = -2257161303770878885L;
    /**
     * The init vector.
     */
    private byte[] initVector;
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.crypto.IvEncodedContent#getInitVector()
     */
    @Override
    public byte[] getInitVector() {
        return initVector;
    }
    
    /**
     * Sets the init vector.
     * 
     * @param iv
     *            the initVector to set
     */
    public void setInitVector(final byte[] iv) {
        initVector = iv;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final int maxLen = 16;
        final StringBuilder builder = new StringBuilder();
        builder.append("IvEncodedContentDto (initVector=");
        builder.append(initVector != null ? Arrays.toString(Arrays.copyOf(initVector,
            Math.min(initVector.length, maxLen))) : null);
        builder.append(", toString()=");
        builder.append(super.toString());
        builder.append(")");
        return builder.toString();
    }
    
}
