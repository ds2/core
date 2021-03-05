/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package ds2.oss.core.xmpp.gcm;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author dstrauss
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonAutoDetect(
    fieldVisibility = JsonAutoDetect.Visibility.ANY,
    getterVisibility = JsonAutoDetect.Visibility.NONE,
    setterVisibility = JsonAutoDetect.Visibility.NONE,
    isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class GcmDownstreamMessage extends BaseJsonContent {
    
    @JsonProperty("collapse_key")
    private String collapseKey;
    @JsonProperty("time_to_live")
    private Long ttl;
    @JsonProperty("delay_while_idle")
    private Boolean delayWhileIdle;
    @JsonProperty("data")
    private Map<String, String> data;
    
    public String getCollapseKey() {
        return collapseKey;
    }
    
    public void setCollapseKey(String collapseKey) {
        this.collapseKey = collapseKey;
    }
    
    public Long getTtl() {
        return ttl;
    }
    
    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }
    
    public Boolean isDelayWhileIdle() {
        return delayWhileIdle;
    }
    
    public void setDelayWhileIdle(Boolean delayWhileIdle) {
        this.delayWhileIdle = delayWhileIdle;
    }
    
    public Map<String, String> getData() {
        return data;
    }
    
    public void setData(Map<String, String> data) {
        this.data = data;
    }
    
    @Override
    public String toString() {
        return new StringBuilder().append("GcmDownstreamMessage{collapseKey=").append(collapseKey).append(", ttl=")
            .append(ttl).append(", delayWhileIdle=").append(delayWhileIdle).append(", data=").append(data).append('}')
            .toString();
    }
    
}
