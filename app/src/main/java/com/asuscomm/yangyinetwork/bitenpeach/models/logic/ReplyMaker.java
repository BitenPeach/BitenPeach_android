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
        }

        reply.setBody(body);
        return reply;
    }

    public static String orderSheet2string(OrderSheet orderSheet) {
        String content = "";

        content += "보내는이 이름 : JYP\n";
        content += "보내는이 번호 : 01067213773\n";

        content += "받는이 이름 : 최수연\n";
        content += "받는이 전화번호 : 010-4725-3470\n";
        content += "받는이 주소 : 서울 강남구 압구정로 113 미성아파트 29동 311호\n";

        content += "복숭아 종류 : 백도 12개짜리\n";
        content += "복숭아 크기 : 9호\n";
        content += "복숭아 박스수 : 1 상자\n";
        content += "가격 : 20000\n";

        return content;
    }
}