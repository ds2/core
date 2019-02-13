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
package ds2.oss.core.statics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ds2.oss.core.statics.Methods.isBlank;

/**
 * Contract to convert somethings.
 */
public interface Converts {
    /**
     * A logger.
     */
    Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    Pattern LETTERS = Pattern.compile("[a+z]+");

    /**
     * Converts an object into an int.
     *
     * @param o        the object
     * @param defValue a default value
     * @return the converted value, or the default value
     */
    static int toInt(Object o, int defValue) {
        int rc = defValue;
        if (o != null) {
            if (o instanceof Number) {
                final Number number = (Number) o;
                rc = number.intValue();
            } else if (o instanceof String) {
                try {
                    rc = Integer.parseInt(o.toString());
                } catch (NumberFormatException e) {
                    LOG.debug("Error when converting {} to int!", o, e);
                }
            }
        }
        return rc;
    }

    /**
     * Converts a given object into long.
     *
     * @param o        the object
     * @param defValue the default value
     * @return the long value
     */
    static long toLong(Object o, long defValue) {
        long rc = defValue;
        if (o != null) {
            if (o instanceof Number) {
                final Number number = (Number) o;
                rc = number.longValue();
            } else if (o instanceof String) {
                try {
                    rc = Long.parseLong(o.toString());
                } catch (NumberFormatException e) {
                    LOG.debug("Error when converting {} to long!", o, e);
                }
            }
        }
        return rc;
    }

    /**
     * Converts a given string into a url.
     *
     * @param urlStr the url string
     * @return the url object, or null if an error occurred
     */
    static URL toUrl(final String urlStr) {
        URL rc = null;
        try {
            rc = new URL(urlStr);
        } catch (final MalformedURLException e) {
            LOG.debug("Error when converting the given string into a url!", e);
        }
        return rc;
    }

    /**
     * Converts a given string into a uri.
     *
     * @param s the uri string
     * @return the uri object, or null if an error occurred
     */
    static URI toUri(String s) {
        URI rc = null;
        if (!isBlank(s)) {
            try {
                rc = new URI(s);
            } catch (URISyntaxException e) {
                LOG.debug("Error when converting the given string into a uri!", e);
            }
        }
        return rc;
    }

    /**
     * Parses the locale string and returns a matching locale. If the given string
     * does not match any locale string, null is returned.
     *
     * @param s the display name of the locale
     * @return the locale
     */
    static Locale parseLocaleString(String s) {
        Locale rc = null;
        if (!isBlank(s)) {
            Matcher m = LETTERS.matcher(s.toLowerCase());
            String lang;
            String country = "";
            String variant = "";
            if (m.find()) {
                lang = m.group();
                if (m.find()) {
                    country = m.group();
                }
                if (m.find()) {
                    variant = m.group();
                }
                rc = new Locale(lang, country, variant);
            }
        }
        return rc;
    }

    /**
     * Parses a given string assuming to be an ip address to an ip address object. This method MAY invoke
     * the dns lookup.
     *
     * @param ipAddressOrHostname the ip address, or host name
     * @return the inet address
     */
    static InetAddress toInetAddress(String ipAddressOrHostname) {
        InetAddress rc = null;
        if (!isBlank(ipAddressOrHostname)) {
            try {
                rc = InetAddress.getByName(ipAddressOrHostname);
            } catch (UnknownHostException e) {
                LOG.debug("Error when converting the given sequence to ip address: {}", ipAddressOrHostname, e);
            }
        }
        return rc;
    }

    /**
     * Reads an input stream into a string.
     *
     * @param is the input stream
     * @param cs the charset to decode the received bytes
     * @return the string
     */
    static String readFromInputStream(InputStream is, Charset cs) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, cs), 4098);
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = br.readLine()) != null) {
                if (sb.length() > 0) {
                    sb.append('\n');
                }
                sb.append(line);
            }
        } catch (IOException e) {
            LOG.debug("Error when reading the line from the buffer!", e);
        }
        return sb.toString();
    }

    /**
     * Converts a given base64 string back into a string.
     *
     * @param base64EncodedString the base64 string
     * @param targetCharset       the target charset to use to create the string
     * @return the decoded string
     */
    static String convertBase64ToString(String base64EncodedString, Charset targetCharset) {
        if (isBlank(base64EncodedString)) {
            return "";
        }
        if (targetCharset == null) {
            targetCharset = StandardCharsets.UTF_8;
        }
        byte[] mantleBytes = base64EncodedString.getBytes();
        byte[] base64Bytes = Base64.getDecoder().decode(mantleBytes);
        String rc = new String(base64Bytes, targetCharset);
        return rc;
    }

    /**
     * Converts a given string into base64 using the iso-8859-1 charset.
     *
     * @param s             the string to encode
     * @param targetCharset the charset to use to split the given string into bytes
     * @return the base64 string, using iso-8859-1 encoding.
     */
    static String toBase64(String s, Charset targetCharset) {
        byte[] targetBytes = s.getBytes(targetCharset);
        return Base64.getEncoder().encodeToString(targetBytes);
    }
}
