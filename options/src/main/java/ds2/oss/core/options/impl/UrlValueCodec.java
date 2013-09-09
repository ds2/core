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
/**
 * 
 */
package ds2.oss.core.options.impl;

import java.net.MalformedURLException;
import java.net.URL;

import ds2.oss.core.api.options.ValueType;
import ds2.oss.core.options.api.ValueCodec;
import ds2.oss.core.options.api.ValueCodecMarker;

/**
 * A url value codec.
 * 
 * @author dstrauss
 * @version 0.3
 */
@ValueCodecMarker(ValueType.URL)
public class UrlValueCodec implements ValueCodec<URL> {
    
    @Override
    public String toString(final URL v) {
        if (v == null) {
            return null;
        }
        return v.toString();
    }
    
    @Override
    public URL toValue(final String s) {
        URL rc = null;
        try {
            rc = new URL(s);
        } catch (final MalformedURLException e) {
        }
        return rc;
    }
    
}
