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
package ds2.oss.core.webtools;

import ds2.oss.core.webtools.io.WrappingServletOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A wrapping servlet response.
 * 
 * @author dstrauss
 * @version 0.4
 */
public class WrappingServletResponse implements HttpServletResponse {
    /**
     * A logger.
     */
    private static final Logger LOG = Logger.getLogger(WrappingServletResponse.class.getName());
    /**
     * The initial buffer size.
     */
    private int bufferSize = 6000;
    /**
     * The character encoding.
     */
    private String characterEncoding;
    /**
     * The possible content length.
     */
    private int contentLength;
    /**
     * The response content type.
     */
    private String contentType;
    /**
     * The cookies.
     */
    private final Set<Cookie> cookies;
    /**
     * The error message.
     */
    private String errorMsg;
    /**
     * the response headers.
     */
    private final Map<String, String> headers;
    /**
     * The official output stream to use.
     */
    private ByteArrayOutputStream internalOutputStream;
    /**
     * The locale.
     */
    private Locale locale;
    /**
     * A copying output stream.
     */
    private ServletOutputStream outputStream;
    /**
     * The sendRedirect location.
     */
    private String redirectTo;
    /**
     * The return status.
     */
    private int status = SC_OK;
    /**
     * A print writer. Connected to the output stream.
     */
    private PrintWriter writer;
    /**
     * The content length, as long.
     */
    private long contentLengthLong;
    
    /**
     * Inits the wrapper.
     */
    public WrappingServletResponse() {
        cookies = new HashSet<>();
        internalOutputStream = new ByteArrayOutputStream(bufferSize);
        headers = new HashMap<>();
    }
    
    /**
     * Inits the response wrapper.
     * 
     * @param response
     *            the httpServletResponse to copy values from
     */
    public WrappingServletResponse(final HttpServletResponse response) {
        this();
        fill(response);
    }
    
    /**
     * Inits the response wrapper.
     * 
     * @param response
     *            the servletResponse to copy values from
     */
    public WrappingServletResponse(final ServletResponse response) {
        this();
        fill(response);
    }
    
    @Override
    public final void addCookie(final Cookie cookie) {
        cookies.add(cookie);
    }
    
    @Override
    public void addDateHeader(final String name, final long date) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public final void addHeader(final String name, final String value) {
        if (headers.get(name) == null) {
            headers.put(name, value);
        } else {
            // comma list
        }
    }
    
    @Override
    public void addIntHeader(final String name, final int value) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public final boolean containsHeader(final String name) {
        return headers.get(name) != null;
    }
    
    /**
     * Copies all known content types, headers etc. to the given servlet response. Be aware that no
     * content will be sent (no body).
     * 
     * @param e
     *            the target response to copy this object data into
     * @param allowSendError
     *            flag to indicate that sendError can be performed
     * @param allowSendRedirect
     *            flag to indicate that sendRedirect can be performed
     * @return TRUE if content can be sent, otherwise FALSE if the current state is an error or
     *         redirect state.
     * 
     * @throws IOException
     *             if an error occurred.
     */
    public final boolean copyHeadersInto(final HttpServletResponse e, final boolean allowSendError,
        final boolean allowSendRedirect) throws IOException {
        if (e.isCommitted()) {
            LOG.warning("Response already sent, cannot add new values.");
            return false;
        }
        if ((errorMsg != null) && allowSendError) {
            e.sendError(status, errorMsg);
            return false;
        }
        if ((redirectTo != null) && allowSendRedirect) {
            e.sendRedirect(redirectTo);
            return false;
        }
        e.reset();
        if (status > 0) {
            e.setStatus(status);
        }
        e.setContentType(getContentType());
        if (contentLength > 0) {
            e.setContentLength(getContentLength());
        }
        if (contentLengthLong > 0) {
            e.setContentLengthLong(contentLengthLong);
        }
        e.setCharacterEncoding(getCharacterEncoding());
        for (Map.Entry<String, String> hN : headers.entrySet()) {
            e.setHeader(hN.getKey(), hN.getValue());
        }
        for (Cookie c : cookies) {
            e.addCookie(c);
        }
        return true;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @deprecated deprecated
     */
    @SuppressWarnings("deprecation")
    @Deprecated
    @Override
    public final String encodeRedirectUrl(final String url) {
        return url;
    }
    
    @Override
    public final String encodeRedirectURL(final String url) {
        return url;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @deprecated deprecated
     */
    @SuppressWarnings("deprecation")
    @Deprecated
    @Override
    public final String encodeUrl(final String url) {
        return url;
    }
    
    @Override
    public final String encodeURL(final String url) {
        return url;
    }
    
    /**
     * Fills this object with some data from the origin servlet response.
     * 
     * @param origin
     *            the origin response
     */
    private final void fill(final HttpServletResponse origin) {
        fill((ServletResponse) origin);
        final Collection<String> headerNames = origin.getHeaderNames();
        if (headerNames != null) {
            for (String hN : headerNames) {
                setHeader(hN, origin.getHeader(hN));
            }
        }
    }
    
    /**
     * Fills this object with some data.
     * 
     * @param origin
     *            the origin
     */
    private void fill(final ServletResponse origin) {
        setBufferSize(origin.getBufferSize());
        setLocale(origin.getLocale());
        setContentType(origin.getContentType());
        setCharacterEncoding(origin.getCharacterEncoding());
    }
    
    /**
     * Finishes the servlet response. Usually called on end of the filter chain.
     */
    public final void finish() {
        if (writer != null) {
            writer.flush();
            writer.close();
        }
        try {
            internalOutputStream.flush();
            internalOutputStream.close();
        } catch (final IOException e) {
            LOG.log(Level.WARNING, "Error when finishing.", e);
        }
        
    }
    
    @Override
    public final void flushBuffer() throws IOException {
        LOG.finer("Flushing stream...");
        if (writer != null) {
            writer.flush();
        } else {
            internalOutputStream.flush();
        }
    }
    
    @Override
    public final int getBufferSize() {
        return bufferSize;
    }
    
    @Override
    public final String getCharacterEncoding() {
        if (characterEncoding == null) {
            return "ISO-8859-1";
        }
        return characterEncoding;
    }
    
    /**
     * Returns the content length, set by the calling entity.
     * 
     * @return the contentLength
     */
    public final synchronized int getContentLength() {
        return contentLength;
    }
    
    @Override
    public final String getContentType() {
        return contentType;
    }
    
    /**
     * Returns all known cookies.
     * 
     * @return the cookies
     */
    public final synchronized Set<Cookie> getCookies() {
        return cookies;
    }
    
    /**
     * Returns the error message.
     * 
     * @return the errorMsg
     */
    public final synchronized String getErrorMsg() {
        return errorMsg;
    }
    
    @Override
    public final String getHeader(final String arg0) {
        return headers.get(arg0);
    }
    
    @Override
    public final Collection<String> getHeaderNames() {
        return headers.keySet();
    }
    
    /**
     * Returns the current headers.
     * 
     * @return the headers
     */
    public final synchronized Map<String, String> getHeaders() {
        return headers;
    }
    
    @Override
    public final Collection<String> getHeaders(final String arg0) {
        return null;
    }
    
    /**
     * Returns the bytes.
     * 
     * @return the written bytes
     */
    public final byte[] getInternalOutputBytes() {
        try {
            internalOutputStream.flush();
            internalOutputStream.close();
            return internalOutputStream.toByteArray();
        } catch (final IOException e) {
            LOG.log(Level.WARNING, "Error when getting the bytes from the output stream.", e);
        }
        return null;
    }
    
    /**
     * Returns the internal output stream.
     * 
     * @return the internalOutputStream
     */
    public final synchronized ByteArrayOutputStream getInternalOutputStream() {
        return internalOutputStream;
    }
    
    @Override
    public final Locale getLocale() {
        return locale;
    }
    
    @Override
    public final ServletOutputStream getOutputStream() throws IOException {
        if (writer != null) {
            throw new IllegalStateException("You cannot use a stream when a writer is preferred!");
        }
        if (outputStream == null) {
            outputStream = new WrappingServletOutputStream(internalOutputStream);
        }
        return outputStream;
    }
    
    /**
     * Returns the sendRedirect location.
     * 
     * @return the redirectTo location
     */
    public final synchronized String getRedirectTo() {
        return redirectTo;
    }
    
    @Override
    public final int getStatus() {
        return status;
    }
    
    @Override
    public final PrintWriter getWriter() throws IOException {
        if (outputStream != null) {
            throw new IllegalStateException("You cannot use the writer when a stream is preferred!");
        }
        if (writer == null) {
            final OutputStreamWriter osw = new OutputStreamWriter(internalOutputStream, getCharacterEncoding());
            writer = new PrintWriter(osw);
        }
        return writer;
    }
    
    @Override
    public final boolean isCommitted() {
        return false;
    }
    
    @Override
    public final void reset() {
        resetBuffer();
        headers.clear();
        cookies.clear();
    }
    
    @Override
    public final void resetBuffer() {
        internalOutputStream.reset();
    }
    
    @Override
    public final void sendError(final int sc) throws IOException {
        status = sc;
    }
    
    @Override
    public final void sendError(final int sc, final String msg) throws IOException {
        status = sc;
        errorMsg = msg;
    }
    
    @Override
    public final void sendRedirect(final String location) throws IOException {
        redirectTo = location;
    }
    
    @Override
    public final void setBufferSize(final int size) {
        if ((writer != null) || (outputStream != null)) {
            throw new IllegalStateException("Streams have already been established!");
        }
        if (size <= 0) {
            return;
        }
        bufferSize = size;
        internalOutputStream = new ByteArrayOutputStream(bufferSize);
    }
    
    @Override
    public final void setCharacterEncoding(final String charset) {
        if ((writer != null) || (charset == null)) {
            return;
        }
        characterEncoding = charset;
        if (contentType != null) {
            // add ;charset= sequence
        }
    }
    
    @Override
    public final void setContentLength(final int len) {
        contentLength = len;
    }
    
    @Override
    public final void setContentType(final String type) {
        contentType = type;
    }
    
    @Override
    public void setDateHeader(final String name, final long date) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public final void setHeader(final String name, final String value) {
        headers.put(name, value);
    }
    
    @Override
    public final void setIntHeader(final String name, final int value) {
        headers.put(name, "" + value);
    }
    
    @Override
    public final void setLocale(final Locale loc) {
        locale = loc;
        // get charset from locale
    }
    
    @Override
    public final void setStatus(final int sc) {
        status = sc;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @deprecated deprecated
     */
    @SuppressWarnings("deprecation")
    @Deprecated
    @Override
    public final void setStatus(final int sc, final String sm) {
        status = sc;
        errorMsg = sm;
    }
    
    @Override
    public final String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("WrappingServletResponse (bufferSize=").append(bufferSize).append(", ");
        if (characterEncoding != null) {
            builder.append("characterEncoding=").append(characterEncoding).append(", ");
        }
        builder.append("contentLength=").append(contentLength).append(", ");
        if (contentType != null) {
            builder.append("contentType=").append(contentType).append(", ");
        }
        if (cookies != null) {
            builder.append("cookies=").append(cookies).append(", ");
        }
        if (errorMsg != null) {
            builder.append("errorMsg=").append(errorMsg).append(", ");
        }
        if (headers != null) {
            builder.append("headers=").append(headers).append(", ");
        }
        if (locale != null) {
            builder.append("locale=").append(locale).append(", ");
        }
        if (redirectTo != null) {
            builder.append("redirectTo=").append(redirectTo).append(", ");
        }
        builder.append("status=").append(status).append(")");
        return builder.toString();
    }
    
    /**
     * Writes the current state of this response object into a given response object.
     * 
     * @param response
     *            the response to copy values to
     * @throws IOException
     *             if an error occurred
     */
    public final void writeResponse(final HttpServletResponse response) throws IOException {
        copyHeadersInto(response, true, true);
        final byte[] bytes = getInternalOutputBytes();
        if (bytes != null) {
            response.getOutputStream().write(bytes);
        }
    }
    
    @Override
    public void setContentLengthLong(final long len) {
        contentLengthLong = len;
    }
}
