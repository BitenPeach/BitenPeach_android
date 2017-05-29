package com.asuscomm.yangyinetwork.bitenpeach.models.domain.dummy;

import com.asuscomm.yangyinetwork.bitenpeach.models.domain.RawText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaeyoung on 2017. 5. 29..
 */

public class DummyData {
    public static RawText getDummyRawText() {
//        String q = "서울시 동대문구 용두동 193-25번지로 복숭아 3만원 짜리 2박스 보내주세요. 받는사람은 김훈영이고 전화번호는 01041521166 입니다";
        String q = "방금 전화드린 서울에사는 신재영입니다! 사돈집에 보낼꺼 3만원짜리루 단단한거루 보내주세요! 주소: 서울 은평구 불광1동 643번지 북한힐스테이트 7차115동 조재영 Hp 010-6721-3773";
        String phoneNumber = "01067213773";
        RawText rawText = new RawText(phoneNumber,q);
        return rawText;
    }

    public static List<RawText> getDummyRawTexts() {
        List<RawText> rawTexts = new ArrayList<>();
        String phoneNumber = "01067213773";
        String q;

        q = "방금 전화드린 서울에사는 신재영입니다! 사돈집에 보낼꺼 3만원짜리루 단단한거루 보내주세요! 주소: 서울 은평구" +
                " 불광1동 643번지 북한힐스테이트 7차115동 조재영 Hp 010-6721-3773";
        rawTexts.add(new RawText(phoneNumber,q));

        q = "서울시 동대문구 용두동 193-25번지로 복숭아 3만원 짜리 2박스 보내주세요. 받는사람은 김훈영이고 전화번호는" +
                " 01041521166 입니다";
        rawTexts.add(new RawText(phoneNumber,q));

        q = "안녕하세요 저번에 전화드린이우진 입니다 5만원어치 위 주소로 부탁드립니다 경기 분당구 정자동 느티마을" +
                " 302-303 핸드폰은 010-3100-5275 입니다";
        rawTexts.add(new RawText(phoneNumber,q));

        q = "사장님 백도 12개짜리 한상자 택배부탁드려요. 받는사람: 최수연 (010-4725-3470) 받는주소: 서울 강남구 압구정로 " +
                "113 미성아파트 29동 311호 택배 가는 중에 물르더라고요. 선물이라 덜 익은 싱싱한 걸로 보내주세요. " +
                "택비포함 금액 알려주시면 입금하겠습니다.";
        rawTexts.add(new RawText(phoneNumber,q));

        q = "서울시 강북구 삼각산동 sk북하산시티아파트 105동 501호 김양혜 010-9254-5866 33,000원 1박스 주문합니다";
        rawTexts.add(new RawText(phoneNumber,q));

        return rawTexts;
    }
}
