package com.asuscomm.yangyinetwork.bitenpeach.models.domain.dummy;

import com.asuscomm.yangyinetwork.bitenpeach.models.domain.RawText;

/**
 * Created by jaeyoung on 2017. 5. 29..
 */

public class DummyData {
    public static RawText getDummyRawText() {
        String q = "$복숭아주문$ 서울시 동대문구 용두동 193-25번지로 복숭아 3만원 짜리 2박스 보내주세요. 받는사람은 김훈영이고 전화번호는 01041521166 입니다";
        String phoneNumber = "01067213773";
        RawText rawText = new RawText(phoneNumber,q);
        return rawText;
    }
}
