/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.xmpp.gcm;

import java.util.Map;
import org.boon.json.annotations.JsonProperty;

/**
 *
 * @author dstrauss
 */
public class GcmDownstreamMessage extends BaseJsonContent {

    @JsonProperty("collapse_key")
    private String collapseKey;
    @JsonProperty("time_to_live")
    private Long ttl;
    @JsonProperty("delay_while_idle")
    private Boolean delayWhileIdle;
    @JsonProperty("data")
    private Map<String, String> data;
}
