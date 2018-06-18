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

import java.io.IOException;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;

/**
 * A logging servlet input stream.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class LoggingServletInputStream extends ServletInputStream {
    /**
     * The lIS.
     */
    private LoggingInputStream<ServletInputStream> lis;
    
    /**
     * inits the LSIS.
     * 
     * @param orig
     *            the SIS
     */
    public LoggingServletInputStream(final ServletInputStream orig) {
        lis = new LoggingInputStream<>(orig);
    }
    
    @Override
    public final int available() throws IOException {
        return lis.available();
    }
    
    @Override
    public final void close() throws IOException {
        lis.close();
    }
    
    /**
     * Returns the currently logged bytes.
     * 
     * @return the logged bytes.
     */
    public final byte[] getBufferedBytes() {
        return lis.getLoggedBytes();
    }
    
    @Override
    public final synchronized void mark(final int readlimit) {
        lis.mark(readlimit);
    }
    
    @Override
    public final boolean markSupported() {
        return lis.markSupported();
    }
    
    @Override
    public final int read() throws IOException {
        final int rc = lis.read();
        return rc;
    }
    
    @Override
    public final int read(final byte[] b) throws IOException {
        return lis.read(b);
    }
    
    @Override
    public final int read(final byte[] b, final int off, final int len) throws IOException {
        return lis.read(b, off, len);
    }
    
    @Override
    public final int readLine(final byte[] arg0, final int arg1, final int arg2) throws IOException {
        final int rc = lis.getOriginal().readLine(arg0, arg1, arg2);
        if (rc > 0) {
            lis.getBuffer().write(arg0, arg1, rc);
        }
        return rc;
    }
    
    @Override
    public final synchronized void reset() throws IOException {
        lis.reset();
    }
    
    @Override
    public final long skip(final long n) throws IOException {
        return lis.skip(n);
    }
    
    @Override
    public boolean isFinished() {
        return lis.getOriginal().isFinished();
    }
    
    @Override
    public boolean isReady() {
        return lis.getOriginal().isReady();
    }
    
    @Override
    public void setReadListener(final ReadListener readListener) {
        lis.getOriginal().setReadListener(readListener);
    }
    
}
