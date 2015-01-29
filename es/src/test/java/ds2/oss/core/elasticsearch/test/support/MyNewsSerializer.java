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
package ds2.oss.core.elasticsearch.test.support;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import ds2.oss.core.codec.gson.GsonTools;
import ds2.oss.core.codec.gson.api.GsonSerializer;
import ds2.oss.core.elasticsearch.impl.AbstractCodecBase;
import ds2.oss.core.elasticsearch.test.dto.MyNews;

import javax.enterprise.context.Dependent;

/**
 * MyNews serializer.
 * 
 * @author dstrauss
 * 
 * @version 0.2
 */
@GsonSerializer(MyNews.class)
@Dependent
public class MyNewsSerializer extends AbstractCodecBase implements JsonSerializer<MyNews> {
    
    @Override
    public JsonElement serialize(final MyNews src, final Type typeOfSrc, final JsonSerializationContext context) {
        final JsonObject rc = new JsonObject();
        GsonTools.addIfNotNull(rc, "title", src.getTitle());
        GsonTools.addIfNotNull(rc, "author", src.getAuthor());
        GsonTools.addIfNotNull(rc, "message", src.getMsg());
        GsonTools.addIfNotNull(rc, "postDate", fromDate(src.getPostDate()));
        return rc;
    }
}
