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
