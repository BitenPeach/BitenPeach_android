package com.asuscomm.yangyinetwork.bitenpeach.models.logic;

import android.util.Log;

import com.asuscomm.yangyinetwork.bitenpeach.models.domain.OrderSheet;
import com.asuscomm.yangyinetwork.bitenpeach.models.domain.ProcessedText;
import com.asuscomm.yangyinetwork.bitenpeach.utils.firebase.FbDBHelper;

import java.util.HashMap;
import java.util.Iterator;

import static com.asuscomm.yangyinetwork.bitenpeach.models.domain.OrderSheet.COMPONENTS.*;

/**
 * Created by jaeyoung on 2017. 5. 29..
 */

public class OrderSheetFiller {
    private static final String TAG = "JYP/OrderSheetFiller";
    public interface OnFinishListener {
        void onFinish(OrderSheet orderSheet);
    }
    public static void fillOrderSheet(final ProcessedText processedText, final OnFinishListener listener) {
        OrderSheet orderSheet = null;

        FbDBHelper.getInstance().loadOngoingOrderSheet(processedText.getPhoneNumber(),
        new FbDBHelper.OnOrderSheetLoadListener() {
            @Override
            public void onOrderSheetLoad(OrderSheet orderSheet) {
                Log.d(TAG, "onOrderSheetLoad: "+orderSheet);

                if(orderSheet == null) { // Cannot find the prev OrderSheet.
                    orderSheet = new OrderSheet(processedText.getPhoneNumber());
                    orderSheet.setFirstConversation(true);
                } else {
                    orderSheet.setFirstConversation(false);
                }

                HashMap hashMap = processedText.getContent();
                Iterator<String> keys = hashMap.keySet().iterator();
                while(keys.hasNext() ) {
                    String key = keys.next();
                    Object value = hashMap.get(key);

                    String component;

                    component = OrderSheet.COMPONENTS.NAMES[LOCATION_IDX];
                    if(component.equals(key)) {
                        orderSheet.setTo_location((String) value);
//                        if(orderSheet.getTo_location() ==null ) {
//                            Log.d(TAG, "fillOrderSheet: getTo_location() <- "+value);
//                        } else {
//                            Log.d(TAG, "fillOrderSheet: getTo_location() != null");
//                        }
                    }

                    component = OrderSheet.COMPONENTS.NAMES[AMOUNT_OF_MONEY_IDX];
                    if(component.equals(key)) {
                            orderSheet.setPeach_amount_of_money((Double) value);
//                        if(orderSheet.getPeach_amount_of_money() == null ) {
//                            Log.d(TAG, "fillOrderSheet: getPeach_amount_of_money() <- "+value);
//                        } else {
//                            Log.d(TAG, "fillOrderSheet: getPeach_amount_of_money() != null");
//                        }
                    }

                    component = OrderSheet.COMPONENTS.NAMES[FROM_NAME_IDX];
                    if(component.equals(key)) {
                        orderSheet.setFrom_name((String) value);
//                        if(orderSheet.getFrom_name() == null ) {
//                            Log.d(TAG, "fillOrderSheet: getFrom_name() <- "+value);
//                        } else {
//                            Log.d(TAG, "fillOrderSheet: getFrom_name() != null");
//                        }
                    }

                    component = OrderSheet.COMPONENTS.NAMES[TO_PHONE_NUMBER_IDX];
                    if(component.equals(key)) {
                        orderSheet.setTo_phone_number((String) value);
//                        if(orderSheet.getTo_phone_number() ==null ) {
//                            Log.d(TAG, "fillOrderSheet: getTo_phone_number() <- "+value);
//                        } else {
//                            Log.d(TAG, "fillOrderSheet: getTo_phone_number() != null");
//                        }
                    }

                    component = OrderSheet.COMPONENTS.NAMES[TO_NAME_IDX];
                    if(component.equals(key)) {
                        orderSheet.setTo_name((String) value);
//                        if(orderSheet.getTo_name() ==null ) {
//                            Log.d(TAG, "fillOrderSheet: getTo_name() <- "+value);
//                        } else {
//                            Log.d(TAG, "fillOrderSheet: getTo_name() != null");
//                        }
                    }

                    component = OrderSheet.COMPONENTS.NAMES[PEACH_SIZE_IDX];
                    if(component.equals(key)) {
                        orderSheet.setPeach_size((String) value);
//                        if(orderSheet.getPeach_size() ==null ) {
//                            Log.d(TAG, "fillOrderSheet: getPeach_size() <- "+value);
//                        } else {
//                            Log.d(TAG, "fillOrderSheet: getPeach_size() != null");
//                        }
                    }

                    component = OrderSheet.COMPONENTS.NAMES[PEACH_KIND_IDX];
                    if(component.equals(key)) {
                        orderSheet.setPeach_kind((String) value);
//                        if(orderSheet.getPeach_kind() ==null ) {
//                            Log.d(TAG, "fillOrderSheet: getPeach_kind() <- "+value);
//                        } else {
//                            Log.d(TAG, "fillOrderSheet: getPeach_kind() != null");
//                        }
                    }

                    component = OrderSheet.COMPONENTS.NAMES[PEACH_NUMOFBOX_IDX];
                    if(component.equals(key)) {
                        orderSheet.setPeach_numofbox((String)value);
//                        if(orderSheet.getPeach_numofbox() ==null ) {
////                    Integer integer = string2int(value);
//                            Log.d(TAG, "fillOrderSheet: getPeach_numofbox() <- "+value);
//                        } else {
//                            Log.d(TAG, "fillOrderSheet: getPeach_numofbox() != null");
//                        }
                    }
                }
                listener.onFinish(orderSheet);
            }
        });
    }

//    public static Integer string2int(Object value) {
//        Integer result;
//        if(value instanceof String) {
//            result = Integer.parseInt((String) value);
//            Log.d(TAG, "string2int: value is String instance, result="+result);
//        } else {
//            result = (Integer) value;
//        }
//
//        return result;
//    }
}
