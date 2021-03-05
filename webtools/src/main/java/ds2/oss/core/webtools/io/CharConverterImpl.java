/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
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
package ds2.oss.core.webtools.io;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import ds2.oss.core.webtools.api.CharConverter;

/**
 * The basic idea of a char converter.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class CharConverterImpl implements CharConverter {
    /**
     * The charset to perform the conversion.
     */
    private Charset cs;
    
    /**
     * Inits the converter.
     */
    public CharConverterImpl() {
        cs = Charset.forName("utf-8");
    }
    
    @Override
    public final byte[] convertBufferContent(final CharBuffer target, final int rc) {
        return convertCharSequence(target.array(), 0, rc);
    }
    
    /**
     * Converts a given string into its utf8 bytes.
     * 
     * @param c
     *            the string to convert
     * @return the utf8 bytes
     */
    private byte[] convertCharIntern2(final String c) {
        final ByteBuffer bb = cs.encode(c);
        return bb.array();
    }
    
    @Override
    public final byte[] convertChars(final char... c) {
        return convertCharSequence(c, 0, c.length);
    }
    
    @Override
    public final byte[] convertCharSequence(final char[] cbuf, final int off, final int len) {
        final CharBuffer cb = CharBuffer.wrap(cbuf, off, len);
        final ByteBuffer bb = cs.encode(cb);
        return bb.array();
    }
    
    @Override
    public final byte[] convertIntChar(final int rc) {
        return convertCharIntern2("" + (char) rc);
    }
    
}
