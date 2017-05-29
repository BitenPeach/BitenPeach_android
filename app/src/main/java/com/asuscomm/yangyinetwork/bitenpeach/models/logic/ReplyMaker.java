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

        body += "주문서 내용입니다.\n";



        if (OrderSheetValidater.orderSheetFilled(orderSheet)) {
            body += "이상 내용으로 주문이 완료되었습니다. ";
            body += "주문한 내용과 다른사항이 있으시면 말씀해주세요.";
        }

        reply.setBody(body);
        return reply;
    }

    public static String orderSheet2string() {
        return "보내는이 : JYP\n";
    }
}