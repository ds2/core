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

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.CharBuffer;

/**
 * A logger for bufferedReaders.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class LoggingBufferedReader extends BufferedReader {
    /**
     * The logging reader to use.
     */
    private LoggingReader<BufferedReader> br;
    
    /**
     * Inits the reader.
     * 
     * @param b
     *            the buffered reader to wrap
     */
    public LoggingBufferedReader(final BufferedReader b) {
        super(b);
        br = new LoggingReader<>(b);
    }
    
    @Override
    public final void close() throws IOException {
        br.close();
    }
    
    /**
     * Returns the logging reader.
     * 
     * @return the used logging reader
     */
    public final LoggingReader<BufferedReader> getLoggingReader() {
        return br;
    }
    
    @Override
    public final void mark(final int readAheadLimit) throws IOException {
        br.mark(readAheadLimit);
    }
    
    @Override
    public final boolean markSupported() {
        return br.markSupported();
    }
    
    @Override
    public final int read() throws IOException {
        return br.read();
    }
    
    @Override
    public final int read(final char[] cbuf) throws IOException {
        return br.read(cbuf);
    }
    
    @Override
    public final int read(final char[] cbuf, final int off, final int len) throws IOException {
        return br.read(cbuf, off, len);
    }
    
    @Override
    public final int read(final CharBuffer target) throws IOException {
        return br.read(target);
    }
    
    @Override
    public final String readLine() throws IOException {
        final String rc = br.getOriginalReader().readLine();
        if ((rc != null) && (rc.length() > 0)) {
            br.getBuffer().write(rc);
        }
        return rc;
    }
    
    @Override
    public final boolean ready() throws IOException {
        return br.ready();
    }
    
    @Override
    public final void reset() throws IOException {
        br.reset();
    }
    
    @Override
    public final long skip(final long n) throws IOException {
        return br.skip(n);
    }
}
