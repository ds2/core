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

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

/**
 * A logger for a servlet output stream.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class LoggingServletOutputStream extends ServletOutputStream {
    /**
     * The logging OS.
     */
    private LoggingOutputStream<ServletOutputStream> lsos;
    
    /**
     * Inits the logging SOS.
     * 
     * @param e
     *            the original SOS to wrap
     */
    public LoggingServletOutputStream(final ServletOutputStream e) {
        super();
        lsos = new LoggingOutputStream<>(e);
    }
    
    @Override
    public final void write(final int b) throws IOException {
        lsos.write(b);
    }
    
    @Override
    public final void close() throws IOException {
        lsos.close();
    }
    
    /**
     * Returns the buffering output stream that is used by this SOS.
     * 
     * @return the buffered OS
     */
    public final LoggingOutputStream<ServletOutputStream> getBufferedOutputStream() {
        return lsos;
    }
    
    @Override
    public boolean isReady() {
        return lsos.getOriginal().isReady();
    }
    
    @Override
    public void setWriteListener(final WriteListener writeListener) {
        lsos.getOriginal().setWriteListener(writeListener);
    }
}
