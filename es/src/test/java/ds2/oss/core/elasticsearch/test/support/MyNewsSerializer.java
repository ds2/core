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
package ds2.oss.core.elasticsearch.test.support;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import ds2.oss.core.elasticsearch.api.annotations.GsonSerializer;
import ds2.oss.core.elasticsearch.impl.AbstractCodecBase;
import ds2.oss.core.elasticsearch.test.dto.MyNews;

/**
 * MyNews serializer.
 * 
 * @author dstrauss
 * 
 * @version 0.2
 */
@GsonSerializer(MyNews.class)
public class MyNewsSerializer extends AbstractCodecBase implements JsonSerializer<MyNews> {
    
    @Override
    public JsonElement serialize(final MyNews src, final Type typeOfSrc, final JsonSerializationContext context) {
        final JsonObject rc = new JsonObject();
        addIfNotNull(rc, "title", src.getTitle());
        addIfNotNull(rc, "author", src.getAuthor());
        addIfNotNull(rc, "message", src.getMsg());
        addIfNotNull(rc, "postDate", fromDate(src.getPostDate()));
        return rc;
    }
}
