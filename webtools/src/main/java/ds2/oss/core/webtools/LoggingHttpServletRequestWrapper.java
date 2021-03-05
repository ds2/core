/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
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
package ds2.oss.core.webtools;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import ds2.oss.core.webtools.io.LoggingBufferedReader;
import ds2.oss.core.webtools.io.LoggingServletInputStream;

/**
 * A wrapper for servlet requets.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class LoggingHttpServletRequestWrapper extends HttpServletRequestWrapper {
    /**
     * The reader stream to log.
     */
    private LoggingBufferedReader lbr;
    /**
     * The servlet inputstream to log.
     */
    private LoggingServletInputStream lsis;
    
    /**
     * Inits the wrapper.
     * 
     * @param arg0
     *            the original servlet request
     * @throws IOException
     *             if an error occurred.
     */
    public LoggingHttpServletRequestWrapper(final HttpServletRequest arg0) throws IOException {
        super(arg0);
    }
    
    @Override
    public final ServletInputStream getInputStream() throws IOException {
        if (lsis == null) {
            lsis = new LoggingServletInputStream(getRequest().getInputStream());
        }
        return lsis;
    }
    
    /**
     * Returns the currently downloaded bytes. The bytes can be in UTF-8, or in any other charset.
     * It depends on the wrappers being used: if inputstream has been used by the calling entity, we
     * don't know. If getReader has been used, the returned bytes are UTF-8 bytes.
     * 
     * 
     * @return the utf-8 bytes, or any other bytes translated so far.
     */
    public final byte[] getLoggedBytes() {
        byte[] rc = null;
        if (lsis != null) {
            rc = lsis.getBufferedBytes();
        }
        if (lbr != null) {
            rc = lbr.getLoggingReader().getUtf8Bytes();
        }
        return rc;
    }
    
    @Override
    public final BufferedReader getReader() throws IOException {
        if (lbr == null) {
            lbr = new LoggingBufferedReader(getRequest().getReader());
        }
        return lbr;
    }
    
}
