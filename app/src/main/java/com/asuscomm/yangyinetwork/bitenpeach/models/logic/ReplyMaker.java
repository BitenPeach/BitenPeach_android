package com.asuscomm.yangyinetwork.bitenpeach.models.logic;

import com.asuscomm.yangyinetwork.bitenpeach.models.domain.OrderSheet;
import com.asuscomm.yangyinetwork.bitenpeach.models.domain.Reply;

/**
 * Created by jaeyoung on 2017. 5. 30..
 */

public class ReplyMaker {
    public static Reply makeReply(OrderSheet orderSheet) {
        Reply reply = new Reply();
        String body = "";

        body += "주문서 내용입니다.\n\n";

        body += orderSheet2string(orderSheet);


        if (OrderSheetValidater.orderSheetFilled(orderSheet)) {
            body += "\n이상 내용으로 주문이 완료되었습니다. ";
            body += "주문한 내용과 다른사항이 있으시면 말씀해주세요.";
        } else {
            body += "\n주문을 하려면 이하의 내용이 필요합니다.\n";
            body += orderSheet2unsatisfiedList(orderSheet);
            body += "\n을 보내주세요. ";
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
            content += "가격 : "+entity+"\n";
        }

        return content;
    }

    public static String orderSheet2unsatisfiedList(OrderSheet orderSheet) {
        String content = "";
        Object entity;

        entity = orderSheet.getFrom_name();
        if(entity == null) {
            content += "보내는이 이름\n";
        }
        entity = orderSheet.getFrom_phone_number();
        if(entity == null) {
            content += "보내는이 번호\n";
        }

        entity = orderSheet.getTo_name();
        if(entity == null) {
            content += "받는이 이름\n";
        }
        entity = orderSheet.getTo_phone_number();
        if(entity == null) {
            content += "받는이 전화번호\n";
        }
        entity = orderSheet.getTo_location();
        if(entity == null) {
            content += "받는이 주소\n";
        }

        entity = orderSheet.getPeach_kind();
        if(entity == null) {
            content += "복숭아 종류\n";
        }
        entity = orderSheet.getPeach_size();
        if(entity == null) {
            content += "복숭아 크기\n";
        }
        entity = orderSheet.getPeach_numofbox();
        if(entity == null) {
            content += "복숭아 박스수\n";
        }
        entity = orderSheet.getPeach_amount_of_money();
        if(entity == null) {
            content += "가격\n";
        }

        return content;
    }
}