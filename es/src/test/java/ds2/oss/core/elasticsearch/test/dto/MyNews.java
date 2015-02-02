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
package ds2.oss.core.elasticsearch.test.dto;

import java.util.Date;

import ds2.oss.core.api.es.FieldTypes;
import ds2.oss.core.api.es.IndexTypes;
import ds2.oss.core.api.es.PropertyMapping;
import ds2.oss.core.api.es.TypeMapping;

/**
 * The Dto to persist.
 * 
 * @author dstrauss
 * @version 0.2
 */
@TypeMapping(value = "news", compressThreshold = "100b")
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
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof MyNews)) {
            return false;
        }
        final MyNews other = (MyNews) obj;
        if (author == null) {
            if (other.author != null) {
                return false;
            }
        } else if (!author.equals(other.author)) {
            return false;
        }
        if (postDate == null) {
            if (other.postDate != null) {
                return false;
            }
        } else if (!postDate.equals(other.postDate)) {
            return false;
        }
        if (title == null) {
            if (other.title != null) {
                return false;
            }
        } else if (!title.equals(other.title)) {
            return false;
        }
        return true;
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
     * Returns the message.
     * 
     * @return the msg
     */
    public String getMsg() {
        return msg;
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
     * Returns the title.
     * 
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (author == null ? 0 : author.hashCode());
        result = prime * result + (postDate == null ? 0 : postDate.hashCode());
        result = prime * result + (title == null ? 0 : title.hashCode());
        return result;
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
     * Sets the post date.
     * 
     * @param postDate
     *            the postDate to set
     */
    public void setPostDate(final Date postDate) {
        this.postDate = postDate;
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
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("MyNews (title=");
        builder.append(title);
        builder.append(", msg=");
        builder.append(msg);
        builder.append(", postDate=");
        builder.append(postDate);
        builder.append(", author=");
        builder.append(author);
        builder.append(")");
        return builder.toString();
    }
    
}
