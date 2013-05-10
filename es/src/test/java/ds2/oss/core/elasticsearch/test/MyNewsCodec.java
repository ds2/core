/**
 * 
 */
package ds2.oss.core.elasticsearch.test;

import java.io.IOException;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import ds2.oss.core.elasticsearch.api.EsCodec;
import ds2.oss.core.elasticsearch.api.TypeCodec;
import ds2.oss.core.elasticsearch.api.TypeMapping;

/**
 * A codec.
 * 
 * @author dstrauss
 * @version 0.2
 */
@EsCodec(MyNews.class)
@ApplicationScoped
public class MyNewsCodec implements TypeCodec<MyNews> {
    
    /**
     * Inits the codec.
     */
    public MyNewsCodec() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public String toJsonMap(final MyNews t) {
        try {
            XContentBuilder builder =
                XContentFactory.jsonBuilder().startObject()
                    .field("title", t.getTitle())
                    .field("postDate", t.getPostDate())
                    .field("message", t.getMsg())
                    .field("author", t.getAuthor()).endObject();
            return builder.string();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public MyNews toDto(final Class<MyNews> c, final Map<String, Object> o) {
        return null;
    }
    
    @Override
    public String getIndex() {
        return MyNews.class.getAnnotation(TypeMapping.class).useIndex();
    }
    
}
