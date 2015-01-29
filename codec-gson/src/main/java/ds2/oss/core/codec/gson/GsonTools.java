/**
 * 
 */
package ds2.oss.core.codec.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Some handy tools methods to deal with gson.
 * 
 * @author dstrauss
 *
 */
public class GsonTools {
    
    public static void addIfNotNull(final JsonObject obj, final String prop, final String val) {
        if (val == null) {
            return;
        }
        obj.addProperty(prop, val);
    }
    
    public static String getAsString(final JsonObject obj, final String fieldName) {
        JsonElement s = obj.get(fieldName);
        if (s == null || s.isJsonNull()) {
            return null;
        }
        return s.getAsString();
    }
    
    private GsonTools() {
        // ignore
    }
    
}
