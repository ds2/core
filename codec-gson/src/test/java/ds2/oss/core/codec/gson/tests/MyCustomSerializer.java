/**
 * 
 */
package ds2.oss.core.codec.gson.tests;

import java.lang.reflect.Type;
import java.util.Date;

import javax.enterprise.context.Dependent;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import ds2.oss.core.codec.gson.api.GsonSerializer;

/**
 * @author dstrauss
 *
 */
@GsonSerializer(Date.class)
@Dependent
public class MyCustomSerializer implements JsonSerializer<Date> {
    
    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        JsonElement rc = new JsonPrimitive("datum");
        return rc;
    }
    
}
