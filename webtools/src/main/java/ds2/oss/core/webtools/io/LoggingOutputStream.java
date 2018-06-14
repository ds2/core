/*
 * Copyright 2018 DS/2 <dstrauss@ds-2.de>
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
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * A logging output stream.
 *
 * @param <E> the original type of the output stream
 * @author dstrauss
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
    private int maxLength = 0;

    /**
     * Inits the output stream logger.
     *
     * @param e the original output stream to wrap. Nullable.
     */
    public LoggingOutputStream(final E e) {
        this(e, 100);
    }

    /**
     * Inits the LOS with the given output stream and an initial capacity for the back buffer.
     *
     * @param e            the output stream
     * @param initCapacity the initial capacity
     */
    public LoggingOutputStream(final E e, int initCapacity) {
        orig = e;
        baos = new ByteArrayOutputStream(initCapacity);
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
        if (maxLength > 0 && baos.size() > maxLength) {
            return;
        }
        baos.write(b);
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * Returns a string representation of the byte stream using the given charset as base.
     *
     * @param cs the charset to decode the bytes
     * @return the string version of the currently stored bytes
     */
    public String getBytesAsString(Charset cs) {
        byte[] data = baos.toByteArray();
        StringBuilder sb = new StringBuilder(Math.max(maxLength, 1024 * 8));
        sb.append(new String(data, 0, data.length, cs));
        if (data.length > maxLength && maxLength > 0) {
            sb.append("...more...");
        }
        return sb.toString();
    }

}
