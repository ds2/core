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
