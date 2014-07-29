/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.xmpp.gcm;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author dstrauss
 */
public class BaseJsonContent {

    @JsonProperty("message_type")
    private String messageType;
    @JsonProperty("message_id")
    private String mesageId;
    @JsonProperty("from")
    private String from;
    @JsonProperty("to")
    private String to;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMesageId() {
        return mesageId;
    }

    public void setMesageId(String mesageId) {
        this.mesageId = mesageId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

}
