package com.asuscomm.yangyinetwork.bitenpeach.models;

import java.util.HashMap;

/**
 * Created by jaeyoung on 2017. 5. 29..
 */

public class ProcessedText {
    private String rawTextId;
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
    private HashMap<String, String> content;
}
