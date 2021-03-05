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
package ds2.oss.core.api;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * URI builder tool.
 *
 * @author dstrauss
 * @version 0.3
 */
public final class UriTool {

    /**
     * A logger.
     */
    private static final Logger LOG = Logger.getLogger(UriTool.class.getName());

    /**
     * Creates a tool from a given string that is considered to be a uri string.
     *
     * @param string the string considered to be a uri
     * @return the uri tool
     */
    public static UriTool createFrom(final String string) {
        try {
            return UriTool.createFrom(new URI(string));
        } catch (final URISyntaxException e) {
            LOG.log(Level.WARNING, "Error when creating the URI from " + string, e);
        }
        return null;
    }

    /**
     * Creates a new uri tool.
     *
     * @param u the base uri
     * @return the uri tool
     */
    public static UriTool createFrom(final URI u) {
        if (u == null) {
            throw new IllegalArgumentException("No uri given to parse!");
        }
        final UriTool rc = new UriTool();
        rc.authority = u.getAuthority();
        rc.fragment = u.getFragment();
        rc.host = u.getHost();
        rc.path = u.getPath();
        rc.port = u.getPort();
        rc.query = u.getQuery();
        rc.queryParams = fillQueryParamsFromQuery(rc.query);
        rc.scheme = u.getScheme();
        rc.userInfo = u.getUserInfo();
        return rc;
    }

    /**
     * Converts a given query string into a map.
     *
     * @param query2 the query string
     * @return the map
     */
    private static Map<String, List<String>> fillQueryParamsFromQuery(final String query2) {
        final Map<String, List<String>> rc = new HashMap<>();
        if (query2 == null || query2.length() <= 0) {
            return rc;
        }
        try (Scanner scanner = new Scanner(query2);) {
            scanner.useDelimiter("&");
            while (scanner.hasNext()) {
                final String s = scanner.next();
                final String[] splits = s.split("=");
                final String paramName = splits[0];
                final String paramVal = splits[1];
                List<String> vals = rc.get(paramName);
                if (vals == null) {
                    vals = new ArrayList<>();
                    rc.put(paramName, vals);
                }
                vals.add(paramVal);
            }
        }
        return rc;
    }

    /**
     * Converts the given query map into a single string.
     *
     * @param queryParams2 the map
     * @return the single string
     * @throws UnsupportedEncodingException if the encoding is unsupported
     */
    private static String setQueryString(final Map<String, List<String>> queryParams2)
            throws UnsupportedEncodingException {
        final StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        final List<String> paramNames = new ArrayList<>(queryParams2.keySet());
        Collections.sort(paramNames);
        for (String paramName : paramNames) {
            final List<String> paramVals = queryParams2.get(paramName);
            for (String val : paramVals) {
                if (!isFirst) {
                    sb.append("&");
                }
                sb.append(URLEncoder.encode(paramName, "utf-8"));
                sb.append("=");
                sb.append(URLEncoder.encode(val, "utf-8"));
                isFirst = false;
            }
        }
        if (sb.length() <= 0) {
            return null;
        }
        return sb.toString();
    }

    /**
     * The authority.
     */
    private String authority;
    /**
     * The fragment.
     */
    private String fragment;
    /**
     * The host.
     */
    private String host;
    /**
     * The path.
     */
    private String path;
    /**
     * The port.
     */
    private int port;

    /**
     * The query string.
     */
    private String query;

    /**
     * The query params map.
     */
    private Map<String, List<String>> queryParams;

    /**
     * The scheme.
     */
    private String scheme;

    /**
     * The user info.
     */
    private String userInfo;

    /**
     * Dummy constructor.
     */
    private UriTool() {
        queryParams = new HashMap<>();
    }

    /**
     * Adds the values to a query parameter.
     *
     * @param name the query parameter
     * @param val the new values
     * @return this tool
     */
    public UriTool addQueryParam(final String name, final String... val) {
        List<String> valList = getQueryParameter(name);
        valList.addAll(Arrays.asList(val));
        return this;
    }

    /**
     * Builds the final uri.
     *
     * @return the uri
     * @throws URISyntaxException in case of a syntax exception
     * @throws UnsupportedEncodingException in case of an unsupported encoding
     * exception
     */
    public URI build() throws URISyntaxException, UnsupportedEncodingException {
        query = setQueryString(queryParams);
        URI rc;
        if (authority != null) {
            rc = new URI(scheme, authority, path, query, fragment);
        } else {
            rc = new URI(scheme, userInfo, host, port, path, query, fragment);
        }
        return rc;
    }

    /**
     * Builds the final uri. Besides {@link #build()}, this method will not
     * throw an exception if an error occurred but will return null.
     *
     * @return the uri
     */
    public URI buildFinally() {
        try {
            return build();
        } catch (final UnsupportedEncodingException | URISyntaxException e) {
            LOG.log(Level.FINE, "Error when creating the URI!", e);
        }
        return null;
    }

    private List<String> getQueryParameter(final String n) {
        List<String> rc = queryParams.get(n);
        if (rc == null) {
            rc = new ArrayList<>();
            queryParams.put(n, rc);
        }
        return rc;
    }

    /**
     * Adds a path segment to the current path.
     *
     * @param pathSegment the path segment to add
     * @return this tool
     */
    public UriTool path(final String pathSegment) {
        if (path == null) {
            path = "";
        }
        try {
            path += "/" + URLEncoder.encode(pathSegment, "utf-8");
        } catch (final UnsupportedEncodingException e) {
            LOG.log(Level.FINE, "Error when adding a path segment!", e);
        }
        return this;
    }

    /**
     * Sets a new host name.
     *
     * @param hostname the new host name
     * @return this tool
     */
    public UriTool setHost(final String hostname) {
        host = hostname;
        return this;
    }

    /**
     * Sets the path.
     *
     * @param p the path
     * @return this tool
     */
    public UriTool setPath(final String p) {
        path = p;
        return this;
    }

    /**
     * Sets a new value for a query param.
     *
     * @param name the query parameter name
     * @param val the new values
     * @return this tool
     */
    public UriTool setQueryParam(final String name, final String... val) {
        List<String> valList = getQueryParameter(name);
        valList.clear();
        valList.addAll(Arrays.asList(val));
        return this;
    }

    /**
     * Puts a slash char in front of the path if not already done.
     */
    public void setSlashBeforePath() {
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
    }

    /**
     * Clears the host and port.
     */
    public void clearHost() {
        host = null;
        port = -1;
    }

    /**
     * Clears the scheme and the host data.
     */
    public void makeDirectoryOnly() {
        clearHost();
        scheme = null;
    }
}
