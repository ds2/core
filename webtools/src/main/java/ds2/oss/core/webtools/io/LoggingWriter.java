package ds2.oss.core.webtools.io;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * A logging writer.
 * 
 * @author dstrauss
 * @param <E>
 *            The type of the writer
 * @version 0.3
 */
public class LoggingWriter<E extends Writer> extends Writer {
    /**
     * The original writer.
     */
    private E orig;
    /**
     * The char array writer, acting as buffer.
     */
    private CharArrayWriter caw;
    
    /**
     * Inits the logging writer.
     * 
     * @param w
     *            the original writer to wrap
     */
    public LoggingWriter(final E w) {
        super();
        orig = w;
        caw = new CharArrayWriter();
    }
    
    /*
     * (non-Javadoc)
     * @see java.io.Writer#write(char[], int, int)
     */
    @Override
    public final void write(final char[] cbuf, final int off, final int len) throws IOException {
        orig.write(cbuf, off, len);
        caw.write(cbuf, off, len);
    }
    
    /*
     * (non-Javadoc)
     * @see java.io.Writer#flush()
     */
    @Override
    public final void flush() throws IOException {
        orig.flush();
        caw.flush();
    }
    
    /*
     * (non-Javadoc)
     * @see java.io.Writer#close()
     */
    @Override
    public final void close() throws IOException {
        orig.close();
        caw.close();
    }
    
    /**
     * Returns the buffered chars.
     * 
     * @return the buffered chars
     */
    public final char[] getBufferedChars() {
        return caw.toCharArray();
    }
    
}
