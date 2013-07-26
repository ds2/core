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

import java.io.IOException;
import java.util.Map;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import ds2.oss.core.elasticsearch.api.annotations.EsCodec;
import ds2.oss.core.elasticsearch.api.annotations.TypeMapping;
import ds2.oss.core.elasticsearch.test.NewsCodec;
import ds2.oss.core.elasticsearch.test.dto.MyNews;

/**
 * A codec.
 * 
 * @author dstrauss
 * @version 0.2
 */
@EsCodec(MyNews.class)
public class MyNewsCodec implements NewsCodec {
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(MyNewsCodec.class);
    
    @Override
    public String toJson(final MyNews t) {
        final Gson g = new Gson();
        final String gsonString = g.toJson(t);
        LOG.debug("Gson says: {}", gsonString);
        try {
            final XContentBuilder builder =
                XContentFactory.jsonBuilder().startObject().field("title", t.getTitle())
                    .field("postDate", t.getPostDate()).field("message", t.getMsg()).field("author", t.getAuthor())
                    .endObject();
            return builder.string();
        } catch (final IOException e) {
            LOG.warn("Error when generating the JSON", e);
        }
        return null;
    }
    
    @Override
    public String getMapping() {
        XContentBuilder xbMapping = null;
        try {
            xbMapping =
                XContentFactory.jsonBuilder().startObject()
                    .startObject(MyNews.class.getAnnotation(TypeMapping.class).value()).startObject("properties");
            xbMapping.startObject("source").field("type", "string").endObject();
            xbMapping.startObject("title").field("type", "string").endObject();
            xbMapping.startObject("description").field("type", "string").endObject();
            xbMapping.startObject("author").field("type", "string").endObject();
            xbMapping.startObject("link").field("type", "string").field("index", "not_analyzed").endObject();
            xbMapping.endObject().endObject().endObject();
            return xbMapping.string();
        } catch (final IOException e) {
            LOG.error("Error when setting up the mapping!", e);
        }
        return null;
    }
    
    @Override
    public boolean refreshOnIndexing() {
        return true;
    }
    
    @Override
    public boolean replicateOnIndexing() {
        return false;
    }
    
    @Override
    public boolean matches(final Class<?> c) {
        return c.isAssignableFrom(MyNews.class);
    }
    
    @Override
    public String getIndexTypeName() {
        return MyNews.class.getAnnotation(TypeMapping.class).value();
    }
    
    @Override
    public MyNews toDto(final Map<String, Object> o) {
        return null;
    }
    
    @Override
    public String getIndexName() {
        return null;
    }
    
    @Override
    public MyNews toDto(final String jsonContent) {
        if ((jsonContent == null) || (jsonContent.trim().length() <= 0)) {
            throw new IllegalArgumentException("No content given to transform into a dto!");
        }
        LOG.debug("Json to convert is {}", jsonContent);
        final Gson g = new Gson();
        final MyNews rc = g.fromJson(jsonContent, MyNews.class);
        LOG.debug("Gson made {}", rc);
        return rc;
    }
    
}
