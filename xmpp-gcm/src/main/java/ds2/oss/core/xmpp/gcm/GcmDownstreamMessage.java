/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.xmpp.gcm;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

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
        isGetterVisibility = JsonAutoDetect.Visibility.NONE
)
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
        return new StringBuilder().append("GcmDownstreamMessage{collapseKey=").append(collapseKey).append(", ttl=").append(ttl).append(", delayWhileIdle=").append(delayWhileIdle).append(", data=").append(data).append('}').toString();
    }

}
