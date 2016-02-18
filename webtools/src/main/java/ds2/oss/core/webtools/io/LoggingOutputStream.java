/*
 * Copyright 2012-2015 Dirk Strauss
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
