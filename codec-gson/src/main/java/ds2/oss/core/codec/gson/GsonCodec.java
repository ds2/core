/*
 * Copyright 2012-2014 Dirk Strauss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ds2.oss.core.codec.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.JsonCodec;
import ds2.oss.core.codec.gson.api.GsonDeserializer;
import ds2.oss.core.codec.gson.api.GsonSerializer;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

/**
 * The GSON implementation for a json codec.
 *
 * @author dstrauss
 * @version 0.3
 */
@ApplicationScoped
public class GsonCodec implements JsonCodec {

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
    public <E> E decode(String z, Class<E> c) throws CoreException {
        final E rc = gson.fromJson(z, c);
        return rc;
    }

    @Override
    public String encode(Object t) throws CoreException {
        if (t == null) {
            throw new IllegalArgumentException("No type given to encode!");
        }
        final String rc = gson.toJson(t);
        return rc;
    }

    @Override
    public Object decode(String a) throws CoreException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
