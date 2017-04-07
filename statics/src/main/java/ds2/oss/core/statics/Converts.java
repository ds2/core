package ds2.oss.core.statics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.net.*;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if (!Methods.isBlank(s)) {
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
        if (!Methods.isBlank(s)) {
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
        if (!Methods.isBlank(ipAddressOrHostname)) {
            try {
                rc = InetAddress.getByName(ipAddressOrHostname);
            } catch (UnknownHostException e) {
                LOG.debug("Error when converting the given sequence to ip address: {}", ipAddressOrHostname, e);
            }
        }
        return rc;
    }
}
