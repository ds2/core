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
package ds2.oss.core.elasticsearch.test.dto;

import java.util.Date;

import ds2.oss.core.elasticsearch.api.FieldTypes;
import ds2.oss.core.elasticsearch.api.IndexTypes;
import ds2.oss.core.elasticsearch.api.annotations.PropertyMapping;
import ds2.oss.core.elasticsearch.api.annotations.TypeMapping;

/**
 * The Dto to persist.
 * 
 * @author dstrauss
 * @version 0.2
 */
@TypeMapping(useIndex = "index1", value = "news")
public class MyNews {
    /**
     * The title.
     */
    @PropertyMapping
    private String title;
    /**
     * The message.
     */
    @PropertyMapping
    private String msg;
    /**
     * The post date.
     */
    @PropertyMapping(type = FieldTypes.DATE)
    private Date postDate;
    /**
     * The author.
     */
    @PropertyMapping(index = IndexTypes.NOT_ANALYZED)
    private String author;
    
    /**
     * INits the dto.
     */
    public MyNews() {
        // nothing special to do
    }
    
    /**
     * Returns the title.
     * 
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Sets the title.
     * 
     * @param title
     *            the title to set
     */
    public void setTitle(final String title) {
        this.title = title;
    }
    
    /**
     * Returns the message.
     * 
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }
    
    /**
     * Sets the message.
     * 
     * @param msg
     *            the msg to set
     */
    public void setMsg(final String msg) {
        this.msg = msg;
    }
    
    /**
     * Returns the post date.
     * 
     * @return the postDate
     */
    public Date getPostDate() {
        return postDate;
    }
    
    /**
     * Sets the post date.
     * 
     * @param postDate
     *            the postDate to set
     */
    public void setPostDate(final Date postDate) {
        this.postDate = postDate;
    }
    
    /**
     * Returns the author.
     * 
     * @return the author
     */
    public String getAuthor() {
        return author;
    }
    
    /**
     * Sets the author.
     * 
     * @param author
     *            the author to set
     */
    public void setAuthor(final String author) {
        this.author = author;
    }
    
}
