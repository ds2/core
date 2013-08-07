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
    String resolve(String key, Locale loc, Object... params);
  String formatCurrency(Locale locale, double val);
}
