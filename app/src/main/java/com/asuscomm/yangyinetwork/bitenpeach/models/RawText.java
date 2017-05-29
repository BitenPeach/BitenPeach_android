package com.asuscomm.yangyinetwork.bitenpeach.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by jaeyoung on 2017. 4. 7..
 */

@IgnoreExtraProperties
public class RawText {
    private String phoneNumber;
    private String messageBody;
    private String processedTextId;
    private boolean isProcessed;
    private String replyId;
    private boolean isReplied;

    public RawText() {
    }

    public RawText(String phoneNumber, String messageBody) {
        this.phoneNumber = phoneNumber;
        this.messageBody = messageBody;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    @Override
    public String toString() {
        return "RawText{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", messageBody='" + messageBody + '\'' +
                '}';
    }
}
