package com.asuscomm.yangyinetwork.bitenpeach.models.domain;

/**
 * Created by jaeyoung on 2017. 5. 29..
 */

public class Reply {
    private String rawTextId;
    private String body;
    private boolean isSended;

    public Reply() {
    }

    public Reply(String rawTextId, String body, boolean isSended) {
        this.rawTextId = rawTextId;
        this.body = body;
        this.isSended = isSended;
    }

    public String getRawTextId() {
        return rawTextId;
    }

    public void setRawTextId(String rawTextId) {
        this.rawTextId = rawTextId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isSended() {
        return isSended;
    }

    public void setSended(boolean sended) {
        isSended = sended;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "rawTextId='" + rawTextId + '\'' +
                ", body='" + body + '\'' +
                ", isSended=" + isSended +
                '}';
    }

    public String getPhoneNumber() {
        return "01067213773";
    }
}
