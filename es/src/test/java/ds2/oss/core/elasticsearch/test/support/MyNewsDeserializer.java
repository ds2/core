/**
 * 
 */
package ds2.oss.core.elasticsearch.test.support;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import ds2.oss.core.elasticsearch.api.annotations.GsonDeserializer;
import ds2.oss.core.elasticsearch.test.dto.MyNews;

/**
 * MyNews Deserializer.
 * 
 * @author dstrauss
 * @version 0.2
 */
@GsonDeserializer(MyNews.class)
public class MyNewsDeserializer implements JsonDeserializer<MyNews> {
    
    @Override
    public MyNews deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
        throws JsonParseException {
        final MyNews rc = new MyNews();
        rc.setAuthor(json.getAsJsonObject().get("author").getAsString());
        rc.setMsg(json.getAsJsonObject().get("message").getAsString());
        rc.setTitle(json.getAsJsonObject().get("title").getAsString());
        return rc;
    }
    
}
