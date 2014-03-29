/**
 * 
 */
package ds2.oss.core.base.impl;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
     * The port.
     */
    private int port;
    /**
     * The path.
     */
    private String path;
    /**
     * The host.
     */
    private String host;
    /**
     * The fragment.
     */
    private String fragment;
    /**
     * The authority.
     */
    private String authority;
    /**
     * The query string.
     */
    private String query;
    /**
     * The scheme.
     */
    private String scheme;
    /**
     * The user info.
     */
    private String userInfo;
    /**
     * The query params map.
     */
    private Map<String, List<String>> queryParams;
    
    /**
     * Dummy constructor.
     */
    private UriTool() {
        queryParams = new HashMap<>();
    }
    
    /**
     * Creates a new uri tool.
     * 
     * @param u
     *            the base uri
     * @return the uri tool
     */
    public static UriTool createFrom(final URI u) {
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
     * @param query2
     *            the query string
     * @return the map
     */
    private static Map<String, List<String>> fillQueryParamsFromQuery(final String query2) {
        final Map<String, List<String>> rc = new HashMap<>();
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
                }
                vals.add(paramVal);
            }
        }
        return rc;
    }
    
    /**
     * Sets a new host name.
     * 
     * @param hostname
     *            the new host name
     * @return this tool
     */
    public UriTool setHost(final String hostname) {
        this.host = hostname;
        return this;
    }
    
    /**
     * Sets a new value for a query param.
     * 
     * @param name
     *            the query parameter name
     * @param val
     *            the new values
     * @return this tool
     */
    public UriTool setQueryParam(final String name, final String... val) {
        List<String> valList = queryParams.get(name);
        if (valList == null) {
            valList = new ArrayList<>();
        }
        valList.clear();
        for (String givenVal : val) {
            valList.add(givenVal);
        }
        return this;
    }
    
    /**
     * Adds the values to a query parameter.
     * 
     * @param name
     *            the query parameter
     * @param val
     *            the new values
     * @return this tool
     */
    public UriTool addQueryParam(final String name, final String... val) {
        List<String> valList = queryParams.get(name);
        if (valList == null) {
            valList = new ArrayList<>();
        }
        for (String givenVal : val) {
            valList.add(givenVal);
        }
        return this;
    }
    
    /**
     * Sets the path.
     * 
     * @param p
     *            the path
     * @return this tool
     */
    public UriTool setPath(final String p) {
        path = p;
        return this;
    }
    
    /**
     * Builds the final uri.
     * 
     * @return the uri
     * @throws URISyntaxException
     *             in case of a syntax exception
     * @throws UnsupportedEncodingException
     *             in case of an unsupported encoding exception
     */
    public URI build() throws URISyntaxException, UnsupportedEncodingException {
        query = setQueryString(queryParams);
        if (authority != null) {
            return new URI(scheme, authority, path, query, fragment);
        }
        return new URI(scheme, userInfo, host, port, path, query, fragment);
    }
    
    /**
     * Builds the final uri. Besides {@link #build()}, this method will not throw an exception if an
     * error occurred but will return null.
     * 
     * @return the uri
     */
    public URI buildFinally() {
        try {
            return build();
        } catch (final UnsupportedEncodingException | URISyntaxException e) {
            Logger.getLogger(UriTool.class.getName()).log(Level.FINE, "Error when creating the URI!", e);
        }
        return null;
    }
    
    /**
     * Converts the given query map into a single string.
     * 
     * @param queryParams2
     *            the map
     * @return the single string
     * @throws UnsupportedEncodingException
     *             if the encoding is unsupported
     */
    private static String setQueryString(final Map<String, List<String>> queryParams2)
        throws UnsupportedEncodingException {
        final StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (Entry<String, List<String>> entry : queryParams2.entrySet()) {
            final String paramName = entry.getKey();
            final List<String> paramVals = entry.getValue();
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
        return sb.toString();
    }
}
