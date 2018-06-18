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
package ds2.oss.core.options.impl.codecs;

import ds2.oss.core.api.options.ValueType;
import ds2.oss.core.options.api.ValueCodec;
import ds2.oss.core.options.api.ValueCodecMarker;

/**
 * A string value codec.
 * 
 * @author dstrauss
 * @version 0.3
 */
@ValueCodecMarker(ValueType.STRING)
public class StringValueCodec implements ValueCodec<String> {
    
    @Override
    public String toString(final String v) {
        return v;
    }
    
    @Override
    public String toValue(final String s) {
        return s;
    }
    
}
