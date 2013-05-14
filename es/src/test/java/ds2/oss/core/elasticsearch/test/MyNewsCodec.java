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
package ds2.oss.core.elasticsearch.test;

import java.io.IOException;
import java.util.Map;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.elasticsearch.api.EsCodec;
import ds2.oss.core.elasticsearch.api.TypeMapping;

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
    private static final Logger LOG = LoggerFactory
        .getLogger(MyNewsCodec.class);
    
    /**
     * Inits the codec.
     */
    public MyNewsCodec() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public String toJson(final MyNews t) {
        try {
            XContentBuilder builder =
                XContentFactory.jsonBuilder().startObject()
                    .field("title", t.getTitle())
                    .field("postDate", t.getPostDate())
                    .field("message", t.getMsg())
                    .field("author", t.getAuthor()).endObject();
            return builder.string();
        } catch (IOException e) {
            LOG.warn("Error when generating the JSON", e);
        }
        return null;
    }
    
    @Override
    public String getMapping() {
        XContentBuilder xbMapping = null;
        try {
            xbMapping =
                XContentFactory
                    .jsonBuilder()
                    .startObject()
                    .startObject(
                        MyNews.class.getAnnotation(TypeMapping.class).value())
                    .startObject("properties");
            xbMapping.startObject("source").field("type", "string").endObject();
            xbMapping.startObject("title").field("type", "string")
                .field("analyzer", "french").endObject();
            xbMapping.startObject("description").field("type", "string")
                .field("analyzer", "french").endObject();
            xbMapping.startObject("author").field("type", "string").endObject();
            xbMapping.startObject("link").field("type", "string").endObject();
            xbMapping.endObject().endObject().endObject();
            return xbMapping.string();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public String getIndexTypeName() {
        return MyNews.class.getAnnotation(TypeMapping.class).value();
    }
    
    @Override
    public MyNews toDto(final Map<String, Object> o) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public String getIndexName() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
