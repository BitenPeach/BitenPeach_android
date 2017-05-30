package com.asuscomm.yangyinetwork.bitenpeach.models.logic;

import android.util.Log;

import com.asuscomm.yangyinetwork.bitenpeach.models.domain.OrderSheet;
import com.asuscomm.yangyinetwork.bitenpeach.models.domain.Reply;

/**
 * Created by jaeyoung on 2017. 5. 30..
 */

public class ReplyMaker {
    private static final String TAG = "JYP/ReplyMaker";
    public static Reply makeReply(OrderSheet orderSheet) {
        Reply reply = new Reply();
        String body = "";

        if (OrderSheetValidater.orderSheetFilled(orderSheet)) {
            body += "주문서 내용입니다.\n\n";
            orderSheet.setPeach_amount_of_money();
            body += orderSheet2string(orderSheet);

            body += "\n이상 내용으로 주문이 완료되었습니다. ";
            body += "주문한 내용과 다른사항이 있으시면 말씀해주세요.";
        } else {
//            body += orderSheet2string(orderSheet);

            body += orderSheet2unsatisfiedList(orderSheet);
            body += " 어떻게 되나요?";
        }

        reply.setBody(body);
        return reply;
    }

    public static String orderSheet2string(OrderSheet orderSheet) {
        String content = "";
        Object entity;

        entity = orderSheet.getFrom_name();
        if(entity != null) {
            content += "보내는이 이름 : " + entity + "\n";
        }
        entity = orderSheet.getFrom_phone_number();
        if(entity != null) {
            content += "보내는이 번호 : "+entity+"\n";
        }

        entity = orderSheet.getTo_name();
        if(entity != null) {
            content += "받는이 이름 : "+entity+"\n";
        }
        entity = orderSheet.getTo_phone_number();
        if(entity != null) {
            content += "받는이 전화번호 : "+entity+"\n";
        }
        entity = orderSheet.getTo_location();
        if(entity != null) {
            content += "받는이 주소 : "+entity+"\n";
        }

        entity = orderSheet.getPeach_kind();
        if(entity != null) {
            content += "복숭아 종류 : "+entity+"\n";
        }
        entity = orderSheet.getPeach_size();
        if(entity != null) {
            content += "복숭아 크기 : "+entity+"\n";
        }
        entity = orderSheet.getPeach_numofbox();
        if(entity != null) {
            content += "복숭아 박스수 : "+entity+"\n";
        }
        entity = orderSheet.getPeach_amount_of_money();
        if(entity != null) {
            content += "가격 : "+(Double.toString((Double) entity))+"원\n";
        }

        return content;
    }

    public static String orderSheet2unsatisfiedList(OrderSheet orderSheet) {
        String content = "";
        Object entity;
        String suffix = "";

        entity = orderSheet.getFrom_name();
        if(entity == null) {
            content += "보내는이 성함, ";
            suffix = "이";
        }
        entity = orderSheet.getFrom_phone_number();
        if(entity == null) {
            content += "보내는이 번호, ";
            suffix = "가";
        }

        entity = orderSheet.getTo_name();
        if(entity == null) {
            content += "받는이 성함, ";
            suffix = "이";
        }
        entity = orderSheet.getTo_phone_number();
        if(entity == null) {
            content += "받는이 전화번호, ";
            suffix = "가";
        }
        entity = orderSheet.getTo_location();
        if(entity == null) {
            content += "받는이 주소, ";
            suffix = "가";
        }

        entity = orderSheet.getPeach_kind();
        if(entity == null) {
            content += "복숭아 종류, ";
            suffix = "가";
        }
        entity = orderSheet.getPeach_size();
        if(entity == null) {
            content += "복숭아 크기, ";
            suffix = "가";
        }
        entity = orderSheet.getPeach_numofbox();
        if(entity == null) {
            content += "복숭아 박스수, ";
            suffix = "가";
        }
//        entity = orderSheet.getPeach_amount_of_money();
//        if(entity == null) {
//            content += "가격\n";
//        }

        if (content.length() > 0) {
            content = content.substring(0, content.length()-2);
            content += suffix;
            Log.d(TAG, "orderSheet2unsatisfiedList: content="+content);
        }

        if(orderSheet.isFirstConversation()) {
            content = "주문 감사합니다. "+content;
        }

        return content;
    }
}