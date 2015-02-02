/*
 * Copyright 2012-2015 Dirk Strauss
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
package ds2.oss.core.api.dto.impl;

import java.util.Arrays;

import ds2.oss.core.api.crypto.EncodedContent;

/**
 * A simple dto.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class EncodedContentDto implements EncodedContent {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 8010600132495793892L;
    /**
     * The encoded bytes.
     */
    private byte[] encoded;
    
    @Override
    public byte[] getEncoded() {
        return encoded;
    }
    
    /**
     * Sets the encoded bytes.
     * 
     * @param enc
     *            the encoded to set
     */
    public void setEncoded(final byte[] enc) {
        encoded = enc;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final int maxLen = 256;
        final StringBuilder builder = new StringBuilder();
        builder.append("EncodedContentDto (encoded=");
        builder.append(encoded != null ? Arrays.toString(Arrays.copyOf(encoded, Math.min(encoded.length, maxLen)))
            : null);
        builder.append(")");
        return builder.toString();
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(encoded);
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
        if (!EncodedContent.class.isAssignableFrom(obj.getClass()))
            return false;
        final EncodedContent other = (EncodedContent) obj;
        if (!Arrays.equals(encoded, other.getEncoded()))
            return false;
        return true;
    }
}
