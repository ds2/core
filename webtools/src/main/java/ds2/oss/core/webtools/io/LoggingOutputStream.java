package ds2.oss.core.webtools.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * A logging output stream.
 * 
 * @author dstrauss
 * @param <E>
 *            the original type of the output stream
 * @version 0.3
 */
public class LoggingOutputStream<E extends OutputStream> extends OutputStream {
    /**
     * The buffer.
     */
    private ByteArrayOutputStream baos;
    /**
     * The original output stream.
     */
    private E orig;
    
    /**
     * Inits the output stream logger.
     * 
     * @param e
     *            the original output stream to wrap. Nullable.
     * 
     */
    public LoggingOutputStream(final E e) {
        orig = e;
        baos = new ByteArrayOutputStream();
    }
    
    @Override
    public final void close() throws IOException {
        if (orig != null) {
            orig.close();
        }
        baos.close();
    }
    
    @Override
    public final void flush() throws IOException {
        if (orig != null) {
            orig.flush();
        }
        baos.flush();
    }
    
    /**
     * Returns the buffer that contains the copy of all written bytes.
     * 
     * @return the buffer
     */
    public final ByteBuffer getBuffer() {
        return ByteBuffer.wrap(baos.toByteArray());
    }
    
    /**
     * Returns the original output stream.
     * 
     * @return the original output stream. May return null if not specified on creation.
     */
    public final E getOriginal() {
        return orig;
    }
    
    @Override
    public final void write(final int b) throws IOException {
        if (orig != null) {
            orig.write(b);
        }
        baos.write(b);
    }
    
}
