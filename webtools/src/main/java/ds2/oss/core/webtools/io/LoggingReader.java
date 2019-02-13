/*
 * Copyright 2019 DS/2 <dstrauss@ds-2.de>
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

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * A logging reader.
 * 
 * @author dstrauss
 * @param <E>
 *            the type of the reader
 * @version 0.3
 */
public class LoggingReader<E extends Reader> extends Reader {
    /**
     * The array buffer.
     */
    private CharArrayWriter caw;
    /**
     * The original reader.
     */
    private E origReader;
    
    /**
     * Inits the reader.
     * 
     * @param r
     *            the reader to copy values from
     */
    public LoggingReader(final E r) {
        super(r);
        origReader = r;
        caw = new CharArrayWriter();
    }
    
    /*
     * (non-Javadoc)
     * @see java.io.Reader#close()
     */
    @Override
    public final void close() throws IOException {
        origReader.close();
        caw.close();
    }
    
    @Override
    public final boolean equals(final Object obj) {
        return origReader.equals(obj);
    }
    
    /**
     * Returns the buffer to use for additionally put data into it.
     * 
     * @return the buffer
     */
    public final Writer getBuffer() {
        return caw;
    }
    
    /**
     * Returns the original reader.
     * 
     * @return the original reader
     */
    public final E getOriginalReader() {
        return origReader;
    }
    
    /**
     * Returns the current charbuffer as a sequence of utf-8 bytes. This is just a convenience
     * method.
     * 
     * @return the utf-8 bytes of the current char buffer.
     */
    public final byte[] getUtf8Bytes() {
        final Charset cs = Charset.forName("utf-8");
        final char[] c = caw.toCharArray();
        final CharBuffer cb = CharBuffer.wrap(c);
        final ByteBuffer bb = cs.encode(cb);
        // last byte 0????
        return bb.array();
    }
    
    @Override
    public final int hashCode() {
        return origReader.hashCode();
    }
    
    @Override
    public final void mark(final int readAheadLimit) throws IOException {
        origReader.mark(readAheadLimit);
    }
    
    @Override
    public final boolean markSupported() {
        return origReader.markSupported();
    }
    
    @Override
    public final int read() throws IOException {
        final int rc = origReader.read();
        if (rc >= 0) {
            caw.write(rc);
        }
        return rc;
    }
    
    @Override
    public final int read(final char[] cbuf) throws IOException {
        final int rc = origReader.read(cbuf);
        if (rc > 0) {
            caw.write(cbuf, 0, rc);
        }
        return rc;
    }
    
    /*
     * (non-Javadoc)
     * @see java.io.Reader#read(char[], int, int)
     */
    @Override
    public final int read(final char[] cbuf, final int off, final int len) throws IOException {
        final int rc = origReader.read(cbuf, off, len);
        if (rc > 0) {
            caw.write(cbuf, off, rc);
        }
        return rc;
    }
    
    @Override
    public final int read(final CharBuffer target) throws IOException {
        final int rc = origReader.read(target);
        if (rc > 0) {
            caw.write(target.array(), 0, rc);
        }
        return rc;
    }
    
    @Override
    public final boolean ready() throws IOException {
        return origReader.ready();
    }
    
    @Override
    public final void reset() throws IOException {
        origReader.reset();
    }
    
    @Override
    public final long skip(final long n) throws IOException {
        return origReader.skip(n);
    }
    
    @Override
    public final String toString() {
        return origReader.toString();
    }
}
