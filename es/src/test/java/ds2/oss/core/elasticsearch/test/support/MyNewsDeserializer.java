/*
 * Copyright 2012-2015 Dirk Strauss
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

import com.google.gson.*;
import ds2.oss.core.codec.gson.GsonTools;
import ds2.oss.core.codec.gson.api.GsonDeserializer;
import ds2.oss.core.elasticsearch.impl.AbstractCodecBase;
import ds2.oss.core.elasticsearch.test.dto.MyNews;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.Dependent;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Type;

/**
 * MyNews Deserializer.
 *
 * @author dstrauss
 * @version 0.2
 */
@GsonDeserializer(MyNews.class)
@Dependent
public class MyNewsDeserializer extends AbstractCodecBase implements JsonDeserializer<MyNews> {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public MyNews deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        LOG.debug("Trying to fill dto with json: {}", json);
        final MyNews rc = new MyNews();
        final JsonObject obj = json.getAsJsonObject();
        rc.setAuthor(GsonTools.getAsString(obj, "author"));
        rc.setMsg(GsonTools.getAsString(obj, "message"));
        rc.setTitle(GsonTools.getAsString(obj, "title"));
        rc.setPostDate(toDate(GsonTools.getAsString(obj, "postDate")));
        LOG.debug("Returning News: {}", rc);
        return rc;
    }

}
