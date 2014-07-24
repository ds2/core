package ds2.oss.core.webtools.io;

import java.io.PrintWriter;
import java.io.Writer;
import java.nio.CharBuffer;

/**
 * A logging print writer.
 * 
 * @author dstrauss
 * @version 0.3
 */
public final class LoggingPrintWriter extends PrintWriter {
    /**
     * Returns an instance of the logging writer.
     * 
     * @param orig
     *            the original writer
     * @return the logging writer
     */
    public static LoggingPrintWriter getInstance(final PrintWriter orig) {
        final LoggingPrintWriter rc = new LoggingPrintWriter(new LoggingWriter<Writer>(orig));
        return rc;
    }
    
    /**
     * The original writer.
     */
    private LoggingWriter<Writer> orig;
    
    /**
     * Inits the print writer.
     * 
     * @param out
     *            the original writer to use
     */
    private LoggingPrintWriter(final LoggingWriter<Writer> out) {
        super(out);
        orig = out;
    }
    
    /**
     * Returns the currently logged chars.
     * 
     * @return the logged chars
     */
    public CharBuffer getLoggedChars() {
        final CharBuffer cb = CharBuffer.wrap(orig.getBufferedChars());
        return cb;
    }
    
}
