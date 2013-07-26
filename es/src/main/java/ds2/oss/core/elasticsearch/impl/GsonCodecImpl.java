/**
 * 
 */
package ds2.oss.core.elasticsearch.impl;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

import ds2.oss.core.elasticsearch.api.GsonCodec;
import ds2.oss.core.elasticsearch.api.annotations.GsonDeserializer;
import ds2.oss.core.elasticsearch.api.annotations.GsonSerializer;

/**
 * The gson codec.
 * 
 * @author dstrauss
 * @version 0.2
 */
@ApplicationScoped
public class GsonCodecImpl implements GsonCodec {
    /**
     * The serializers.
     */
    @Inject
    @Any
    private Instance<JsonSerializer<?>> serializers;
    /**
     * The deserializers.
     */
    @Inject
    @Any
    private Instance<JsonDeserializer<?>> deserializers;
    
    /**
     * The gson main object.
     */
    private Gson gson;
    
    /**
     * Actions to perform after construction, after CDI.
     */
    @PostConstruct
    public void onLoad() {
        final GsonBuilder gb = new GsonBuilder();
        for (JsonSerializer<?> serializer : serializers) {
            final GsonSerializer gsa = serializer.getClass().getAnnotation(GsonSerializer.class);
            if (gsa != null) {
                final Class<?> c = gsa.value();
                gb.registerTypeAdapter(c, serializer);
            }
        }
        for (JsonDeserializer<?> deserializer : deserializers) {
            final GsonDeserializer gsa = deserializer.getClass().getAnnotation(GsonDeserializer.class);
            if (gsa != null) {
                final Class<?> c = gsa.value();
                gb.registerTypeAdapter(c, deserializer);
            }
        }
        gson = gb.create();
    }
    
    @Override
    public <T> String encode(final T t) {
        final String rc = gson.toJson(t);
        return rc;
    }
    
    @Override
    public <T> T decode(final Class<T> c, final String json) {
        final T rc = gson.fromJson(json, c);
        return rc;
    }
    
}
