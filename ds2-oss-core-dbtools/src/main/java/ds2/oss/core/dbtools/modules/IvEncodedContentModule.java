/*
 * Copyright 2012-2014 Dirk Strauss
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
package ds2.oss.core.dbtools.modules;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

import ds2.oss.core.api.crypto.IvEncodedContent;

/**
 * A db module for iv encoded content.
 * 
 * @author dstrauss
 * @version 0.3
 */
@Embeddable
public class IvEncodedContentModule implements IvEncodedContent {
    
    /**
     * The svuid.
     */
    private static final long serialVersionUID = 4906950450524305880L;
    /**
     * The init vector.
     */
    @Column(name = "enc_iv")
    @Lob
    private byte[] initVector;
    /**
     * The encoded data.
     */
    @Column(name = "enc_data")
    @Lob
    private byte[] encoded;
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.crypto.EncodedContent#getEncoded()
     */
    @Override
    public byte[] getEncoded() {
        return encoded;
    }
    
    /**
     * Sets the init vector data.
     * 
     * @param initVector
     *            the initVector to set
     */
    public void setInitVector(byte[] initVector) {
        this.initVector = initVector;
    }
    
    /**
     * Sets the encoded data.
     * 
     * @param encoded
     *            the encoded to set
     */
    public void setEncoded(byte[] encoded) {
        this.encoded = encoded;
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.oss.core.api.crypto.IvEncodedContent#getInitVector()
     */
    @Override
    public byte[] getInitVector() {
        return initVector;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "IvEncodedContentModule [initVector=" + Arrays.toString(initVector) + ", encoded="
            + Arrays.toString(encoded) + "]";
    }
    
}
