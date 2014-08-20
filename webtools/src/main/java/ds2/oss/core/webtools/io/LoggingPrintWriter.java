/*
 * Copyright 2012-2014 Dirk Strauss
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
