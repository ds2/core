/**
 * 
 */
package ds2.oss.core.api;

import java.util.Locale;

/**
 * THe locale support.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface LocaleSupport {
    String resolve(String resourceBaseName, String key, Locale loc, String... params);
}
