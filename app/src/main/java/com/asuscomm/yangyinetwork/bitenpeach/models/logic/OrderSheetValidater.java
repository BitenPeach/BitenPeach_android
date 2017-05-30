package com.asuscomm.yangyinetwork.bitenpeach.models.logic;

import com.asuscomm.yangyinetwork.bitenpeach.models.domain.OrderSheet;

/**
 * Created by jaeyoung on 2017. 5. 30..
 */

public class OrderSheetValidater {
    public static boolean orderSheetFilled(OrderSheet orderSheet) {
        boolean result = true;
        Object entity;

//        entity = orderSheet.getFrom_name();
//        if(entity == null) {
//            result = false;
//        }
//        entity = orderSheet.getFrom_phone_number();
//        if(entity == null) {
//            result = false;
//        }

        entity = orderSheet.getTo_name();
        if(entity == null) {
            result = false;
        }
        entity = orderSheet.getTo_phone_number();
        if(entity == null) {
            result = false;
        }
        entity = orderSheet.getTo_location();
        if(entity == null) {
            result = false;
        }

        if(!peachInfoFilled(orderSheet)) {
            result = false;
        }

        return result;
    }

    public static boolean peachInfoFilled(OrderSheet orderSheet) {
        boolean result = false;
        Object entity;

//        entity = orderSheet.getPeach_kind();
//        if(entity == null) {
//            result = false;
//        }
//        entity = orderSheet.getPeach_size();
//        if(entity == null) {
//            result = false;
//        }
        entity = orderSheet.getPeach_numofbox();
        if(entity != null) {
            result = true;
        }
        entity = orderSheet.getPeach_amount_of_money();
        if(entity != null) {
            result = true;
        }

        return result;
    }
}
