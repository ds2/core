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

import java.util.Map;

import ds2.oss.core.elasticsearch.api.annotations.EsCodec;
import ds2.oss.core.elasticsearch.impl.AbstractTypeCodec;
import ds2.oss.core.elasticsearch.test.NewsCodec;
import ds2.oss.core.elasticsearch.test.dto.MyNews;

import javax.enterprise.context.ApplicationScoped;

/**
 * A codec.
 * 
 * @author dstrauss
 * @version 0.2
 */
@EsCodec(MyNews.class)
@ApplicationScoped
public class MyNewsCodec extends AbstractTypeCodec<MyNews> implements NewsCodec {
    /**
     * Inits the codec.
     */
    public MyNewsCodec() {
        super(MyNews.class);
    }
    
    @Override
    public boolean refreshOnIndexing() {
        return true;
    }
    
    @Override
    public MyNews toDto(final Map<String, Object> fields) {
        final MyNews rc = new MyNews();
        rc.setAuthor((String) fields.get("author"));
        rc.setMsg((String) fields.get("message"));
        rc.setTitle((String) fields.get("title"));
        rc.setPostDate(toDate((String) fields.get("postDate")));
        return rc;
    }
    
}
