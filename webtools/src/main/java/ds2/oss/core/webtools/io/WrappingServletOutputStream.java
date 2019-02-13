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
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

/**
 * A wrapping SOS.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class WrappingServletOutputStream extends ServletOutputStream {
    /**
     * The output stream to use.
     */
    private OutputStream os;
    
    /**
     * Inits the wsos.
     */
    public WrappingServletOutputStream() {
        this(new ByteArrayOutputStream());
    }
    
    /**
     * Inits the wsos.
     * 
     * @param o
     *            the output stream to use
     */
    public WrappingServletOutputStream(final OutputStream o) {
        os = o;
    }
    
    /**
     * Returns the output stream that was used on construction.
     * 
     * @return the output stream
     */
    public final OutputStream getOutputStream() {
        return os;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public final void write(final int b) throws IOException {
        os.write(b);
    }
    
    @Override
    public boolean isReady() {
        return true;
    }
    
    @Override
    public void setWriteListener(final WriteListener writeListener) {
        // ignored
    }
    
}
