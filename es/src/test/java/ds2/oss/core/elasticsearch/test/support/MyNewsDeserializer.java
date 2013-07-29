/*
 * Copyright 2012-2013 Dirk Strauss
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
import ds2.oss.core.elasticsearch.impl.AbstractCodecBase;
import ds2.oss.core.elasticsearch.test.dto.MyNews;

/**
 * MyNews Deserializer.
 * 
 * @author dstrauss
 * @version 0.2
 */
@GsonDeserializer(MyNews.class)
public class MyNewsDeserializer extends AbstractCodecBase implements JsonDeserializer<MyNews> {
    
    @Override
    public MyNews deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
        throws JsonParseException {
        final MyNews rc = new MyNews();
        rc.setAuthor(json.getAsJsonObject().get("author").getAsString());
        rc.setMsg(json.getAsJsonObject().get("message").getAsString());
        rc.setTitle(json.getAsJsonObject().get("title").getAsString());
        rc.setPostDate(toDate(json.getAsJsonObject().get("postDate").getAsString()));
        return rc;
    }
    
}
