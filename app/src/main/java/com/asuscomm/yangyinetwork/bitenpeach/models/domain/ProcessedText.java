package com.asuscomm.yangyinetwork.bitenpeach.models.domain;

import java.util.HashMap;

/**
 * Created by jaeyoung on 2017. 5. 29..
 */

public class ProcessedText {
    private String rawTextId;
    private String phoneNumber;
    private String intent;
    public interface INTENT {
        String ORDER = "주문";
        String NEWORDER = "새로운주문";
        String SUPPLEMENTARYORDER = "주문보충";
        String CANCEL = "취소";
        String CHAT = "잡담";
    }
    private String orderSheetId;
    private boolean isProcessed;
    private HashMap<String, Object> content;

    public ProcessedText() {
        content = new HashMap<>();
    }

    public String getRawTextId() {
        return rawTextId;
    }

    public void setRawTextId(String rawTextId) {
        this.rawTextId = rawTextId;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public String getOrderSheetId() {
        return orderSheetId;
    }

    public void setOrderSheetId(String orderSheetId) {
        this.orderSheetId = orderSheetId;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }

    public HashMap<String, Object> getContent() {
        return content;
    }

    public void addContent(String key, Object value) {
        this.content.put(key, value);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "ProcessedText{" +
                "rawTextId='" + rawTextId + '\'' +
                ", intent='" + intent + '\'' +
                ", orderSheetId='" + orderSheetId + '\'' +
                ", isProcessed=" + isProcessed +
                ", content=" + content +
                '}';
    }
}
