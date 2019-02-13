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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A logging class for inputstreams.
 * 
 * @author dstrauss
 * @param <E>
 *            the type of the input stream
 * @version 0.3
 */
public class LoggingInputStream<E extends InputStream> extends InputStream {
    /**
     * A logger.
     */
    private static final Logger LOG = Logger.getLogger(LoggingInputStream.class.getName());
    /**
     * The buffer to write to.
     */
    private OutputStream baos;
    /**
     * Flag to indicate if a CLOSE has been requested on the original IS.
     */
    private boolean closed;
    /**
     * The original input stream.
     */
    private E is;
    
    /**
     * Inits the LIS.
     * 
     * @param orig
     *            the original input stream
     */
    public LoggingInputStream(final E orig) {
        if (orig == null) {
            throw new IllegalArgumentException("You must provide an input stream!");
        }
        is = orig;
        baos = new ByteArrayOutputStream();
    }
    
    @Override
    public final int available() throws IOException {
        final int rc = is.available();
        return rc;
    }
    
    @Override
    public final void close() throws IOException {
        is.close();
        baos.close();
        closed = true;
    }
    
    @Override
    public final boolean equals(final Object obj) {
        return is.equals(obj);
    }
    
    /**
     * Returns the buffer that is being used to write the read bytes into.
     * 
     * @return the buffer
     */
    public final OutputStream getBuffer() {
        return baos;
    }
    
    /**
     * Returns the currently logged bytes.
     * 
     * @return the logged bytes
     */
    public final byte[] getLoggedBytes() {
        try {
            baos.flush();
            if (!closed) {
                baos.close();
            }
        } catch (final IOException e) {
            LOG.log(Level.SEVERE, "Error when closing the BAOS", e);
        }
        return ((ByteArrayOutputStream) baos).toByteArray();
    }
    
    /**
     * Returns the original input stream that is being logged.
     * 
     * @return the input stream
     */
    public final E getOriginal() {
        return is;
    }
    
    @Override
    public final int hashCode() {
        return is.hashCode();
    }
    
    @Override
    public final synchronized void mark(final int readlimit) {
        LOG.info("mark has been requested!");
        is.mark(readlimit);
    }
    
    @Override
    public final boolean markSupported() {
        return is.markSupported();
    }
    
    /*
     * (non-Javadoc)
     * @see java.io.InputStream#read()
     */
    @Override
    public final int read() throws IOException {
        final int rc = is.read();
        if (rc >= 0) {
            baos.write(rc);
        }
        return rc;
    }
    
    @Override
    public final int read(final byte[] b) throws IOException {
        final int rc = is.read(b);
        if (rc > 0) {
            baos.write(b, 0, rc);
        }
        return rc;
    }
    
    @Override
    public final int read(final byte[] b, final int off, final int len) throws IOException {
        final int rc = is.read(b, off, len);
        if (rc > 0) {
            baos.write(b, off, rc);
        }
        return rc;
    }
    
    @Override
    public final synchronized void reset() throws IOException {
        LOG.info("reset has been requested!");
        is.reset();
    }
    
    @Override
    public final long skip(final long n) throws IOException {
        LOG.info("skip has been requested!");
        return is.skip(n);
    }
    
    @Override
    public final String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(LoggingInputStream.class.getName());
        sb.append("(");
        sb.append(")");
        return sb.toString();
    }
}
