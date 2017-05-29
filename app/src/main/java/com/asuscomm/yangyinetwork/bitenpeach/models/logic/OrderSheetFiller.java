package com.asuscomm.yangyinetwork.bitenpeach.models.logic;

import android.util.Log;

import com.asuscomm.yangyinetwork.bitenpeach.models.domain.OrderSheet;
import com.asuscomm.yangyinetwork.bitenpeach.models.domain.ProcessedText;

import java.util.HashMap;
import java.util.Iterator;

import static com.asuscomm.yangyinetwork.bitenpeach.models.domain.OrderSheet.COMPONENTS.*;

/**
 * Created by jaeyoung on 2017. 5. 29..
 */

public class OrderSheetFiller {
    private static final String TAG = "JYP/OrderSheetFiller";
    public static OrderSheet fillOrderSheet(ProcessedText processedText) {
        OrderSheet orderSheet;
        if(true) { // Cannot find the prev OrderSheet.
            orderSheet = new OrderSheet(processedText.getPhoneNumber());
        }

        HashMap hashMap = processedText.getContent();
        Iterator<String> keys = hashMap.keySet().iterator();
        while(keys.hasNext() ) {
            String key = keys.next();
            Object value = hashMap.get(key);

            String component;

            component = OrderSheet.COMPONENTS.NAMES[LOCATION_IDX];
            if(component.equals(key)) {
                if(orderSheet.getTo_location() ==null ) {
                    orderSheet.setTo_location((String) value);
                    Log.d(TAG, "fillOrderSheet: getTo_location() <- "+value);
                } else {
                    Log.d(TAG, "fillOrderSheet: getTo_location() != null");
                }
            }

            component = OrderSheet.COMPONENTS.NAMES[AMOUNT_OF_MONEY_IDX];
            if(component.equals(key)) {
                if(orderSheet.getPeach_amount_of_money() == null ) {
                    orderSheet.setPeach_amount_of_money((Double) value);
                    Log.d(TAG, "fillOrderSheet: getPeach_amount_of_money() <- "+value);
                } else {
                    Log.d(TAG, "fillOrderSheet: getPeach_amount_of_money() != null");
                }
            }

            component = OrderSheet.COMPONENTS.NAMES[FROM_NAME_IDX];
            if(component.equals(key)) {
                if(orderSheet.getFrom_name() == null ) {
                    orderSheet.setFrom_name((String) value);
                    Log.d(TAG, "fillOrderSheet: getFrom_name() <- "+value);
                } else {
                    Log.d(TAG, "fillOrderSheet: getFrom_name() != null");
                }
            }

            component = OrderSheet.COMPONENTS.NAMES[TO_PHONE_NUMBER_IDX];
            if(component.equals(key)) {
                if(orderSheet.getTo_phone_number() ==null ) {
                    orderSheet.setTo_phone_number((String) value);
                    Log.d(TAG, "fillOrderSheet: getTo_phone_number() <- "+value);
                } else {
                    Log.d(TAG, "fillOrderSheet: getTo_phone_number() != null");
                }
            }

            component = OrderSheet.COMPONENTS.NAMES[TO_NAME_IDX];
            if(component.equals(key)) {
                if(orderSheet.getTo_name() ==null ) {
                    orderSheet.setTo_name((String) value);
                    Log.d(TAG, "fillOrderSheet: getTo_name() <- "+value);
                } else {
                    Log.d(TAG, "fillOrderSheet: getTo_name() != null");
                }
            }

            component = OrderSheet.COMPONENTS.NAMES[PEACH_SIZE_IDX];
            if(component.equals(key)) {
                if(orderSheet.getPeach_size() ==null ) {
                    orderSheet.setPeach_size((Integer) value);
                    Log.d(TAG, "fillOrderSheet: getPeach_size() <- "+value);
                } else {
                    Log.d(TAG, "fillOrderSheet: getPeach_size() != null");
                }
            }

            component = OrderSheet.COMPONENTS.NAMES[PEACH_KIND_IDX];
            if(component.equals(key)) {
                if(orderSheet.getPeach_kind() ==null ) {
                    orderSheet.setPeach_kind((String) value);
                    Log.d(TAG, "fillOrderSheet: getPeach_kind() <- "+value);
                } else {
                    Log.d(TAG, "fillOrderSheet: getPeach_kind() != null");
                }
            }

            component = OrderSheet.COMPONENTS.NAMES[PEACH_NUMOFBOX_IDX];
            if(component.equals(key)) {
                if(orderSheet.getPeach_numofbox() ==null ) {
                    orderSheet.setPeach_numofbox((Integer) value);
                    Log.d(TAG, "fillOrderSheet: getPeach_numofbox() <- "+value);
                } else {
                    Log.d(TAG, "fillOrderSheet: getPeach_numofbox() != null");
                }
            }
        }

        return orderSheet;
    }
}
