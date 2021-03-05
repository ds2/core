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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import ds2.oss.core.webtools.io.LoggingPrintWriter;
import ds2.oss.core.webtools.io.LoggingServletOutputStream;

/**
 * A logging response wrapper.
 * 
 * @author dstrauss
 * @version 0.3
 */
public class LoggingHttpServletResponseWrapper extends HttpServletResponseWrapper {
    /**
     * A pattern to get any numbers.
     */
    private static final Pattern NUMS_PATTERN = Pattern.compile("\\d+");
    /**
     * The logging SOS.
     */
    private LoggingServletOutputStream lo;
    /**
     * The logging print writer.
     */
    private LoggingPrintWriter pw;
    /**
     * The http response code.
     */
    private int status = HttpServletResponse.SC_OK;
    
    /**
     * Inits the response wrapper.
     * 
     * @param arg0
     *            the response object to wrap
     */
    public LoggingHttpServletResponseWrapper(final HttpServletResponse arg0) {
        super(arg0);
    }
    
    /**
     * Returns the logging print writer instance.
     * 
     * @return the logging print writer,
     */
    public final LoggingPrintWriter getLoggingPrintWriter() {
        return pw;
    }
    
    /**
     * Returns the null header content.
     * 
     * @return the null header
     */
    public final String getNullHeader() {
        return getHeader(null);
    }
    
    @Override
    public final ServletOutputStream getOutputStream() throws IOException {
        if (lo == null) {
            lo = new LoggingServletOutputStream(getResponse().getOutputStream());
        }
        return lo;
    }
    
    /**
     * Returns the buffering servlet output stream.
     * 
     * @return the sos, or null if not available
     */
    public final LoggingServletOutputStream getSos() {
        return lo;
    }
    
    /**
     * Returns the status of the http response.
     * 
     * @return the http status, or 0 if not set
     */
    @Override
    public final int getStatus() {
        return status;
    }
    
    /**
     * Calculates the response status from the null header, and returns it. The original status
     * value will be overridden.
     * 
     * @return the http response status code
     */
    public final int getStatusViaHeaders() {
        final String currentNullHeader = getHeader(null);
        if (currentNullHeader != null) {
            final Matcher m = NUMS_PATTERN.matcher(currentNullHeader);
            if (m.find()) {
                final String rcStr = m.group();
                status = Integer.parseInt(rcStr);
            }
        }
        return status;
    }
    
    @Override
    public final PrintWriter getWriter() throws IOException {
        if (pw == null) {
            pw = LoggingPrintWriter.getInstance(super.getWriter());
        }
        return pw;
    }
    
    @Override
    public final void sendError(final int arg0) throws IOException {
        super.sendError(arg0);
        status = arg0;
    }
    
    @Override
    public final void sendError(final int arg0, final String arg1) throws IOException {
        super.sendError(arg0, arg1);
        status = arg0;
    }
    
    @Override
    public final void setStatus(final int arg0) {
        super.setStatus(arg0);
        status = arg0;
    }
    
}
