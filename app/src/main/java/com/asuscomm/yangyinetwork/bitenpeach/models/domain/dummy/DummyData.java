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
        String phoneNumber = "01067213773";
        List<RawText> rawTexts = new ArrayList<>();
        String q = "방금 전화드린 서울에사는 신재영입니다! 사돈집에 보낼꺼 3만원짜리루 단단한거루 보내주세요! 주소: 서울 은평구 불광1동 643번지 북한힐스테이트 7차115동 조재영 Hp 010-6721-3773";
        rawTexts.add(new RawText(phoneNumber,q));
        q = "서울시 동대문구 용두동 193-25번지로 복숭아 3만원 짜리 2박스 보내주세요. 받는사람은 김훈영이고 전화번호는 01041521166 입니다";
        rawTexts.add(new RawText(phoneNumber,q));
        return rawTexts;
    }
}
