/**
 * 
 */
package ds2.oss.core.elasticsearch.test.support;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import ds2.oss.core.elasticsearch.api.annotations.GsonSerializer;
import ds2.oss.core.elasticsearch.test.dto.MyNews;

/**
 * MyNews serializer.
 * 
 * @author dstrauss
 * 
 * @version 0.2
 */
@GsonSerializer(MyNews.class)
public class MyNewsSerializer implements JsonSerializer<MyNews> {
    /**
     * The date time formatter.
     */
    private SimpleDateFormat sdf;
    
    /**
     * Inits the serializer.
     */
    public MyNewsSerializer() {
        sdf = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss.SSSZZ");
    }
    
    @Override
    public JsonElement serialize(final MyNews src, final Type typeOfSrc, final JsonSerializationContext context) {
        final JsonObject rc = new JsonObject();
        if (src.getTitle() != null) {
            rc.addProperty("title", src.getTitle());
        }
        if (src.getAuthor() != null) {
            rc.addProperty("author", src.getAuthor());
        }
        if (src.getMsg() != null) {
            rc.addProperty("message", src.getMsg());
        }
        if (src.getPostDate() != null) {
            final String str = sdf.format(src.getPostDate());
            rc.addProperty("postDate", str);
        }
        return rc;
    }
}
