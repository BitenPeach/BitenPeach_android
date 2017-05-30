package com.asuscomm.yangyinetwork.bitenpeach.models.logic;

import android.util.Log;

import com.asuscomm.yangyinetwork.bitenpeach.models.domain.OrderSheet;
import com.asuscomm.yangyinetwork.bitenpeach.models.domain.ProcessedText;
import com.asuscomm.yangyinetwork.bitenpeach.models.domain.RawText;
import com.asuscomm.yangyinetwork.bitenpeach.models.domain.Reply;
import com.asuscomm.yangyinetwork.bitenpeach.models.domain.witai.MeaningOfSentence;
import com.asuscomm.yangyinetwork.bitenpeach.models.logic.witai.WitaiParser;
import com.asuscomm.yangyinetwork.bitenpeach.utils.mms.MMSSender;
import com.asuscomm.yangyinetwork.bitenpeach.utils.sms.SMSSender;
import com.asuscomm.yangyinetwork.bitenpeach.utils.firebase.FbDBHelper;
import com.asuscomm.yangyinetwork.bitenpeach.utils.witai.WitaiService;

/**
 * Created by jaeyoung on 2017. 5. 30..
 */

public class RawTextProcesser {
    private static final String TAG = "JYP/RawTextProcesser";

    public static void processRawText(final RawText rawText) {
        FbDBHelper.getInstance().save(rawText);

        WitaiService.getInstance().get(rawText, new WitaiService.OnSuccessListener() {
            @Override
            public void onSuccess(MeaningOfSentence meaningOfSentence) {
                Log.d(TAG, "onResponse: meaningOfSentence="+meaningOfSentence.toString());
                ProcessedText processedText = WitaiParser.witaiParser(meaningOfSentence);
                Log.d(TAG, "onResponse: processedText="+processedText.toString());
                processedText.setPhoneNumber(rawText.getPhoneNumber());
                FbDBHelper.getInstance().save(processedText);

                OrderSheetFiller.fillOrderSheet(processedText, new OrderSheetFiller.OnFinishListener() {
                    @Override
                    public void onFinish(OrderSheet orderSheet) {
                        Log.d(TAG, "onResponse: orderSheet="+orderSheet.toString());
                        orderSheet.setFrom_phone_number(rawText.getPhoneNumber());
                        FbDBHelper.getInstance().save(orderSheet);

                        Reply reply = ReplyMaker.makeReply(orderSheet);
                        reply.setPhoneNumber(rawText.getPhoneNumber());
                        Log.i(TAG, "onResponse: reply="+reply.toString());
                        FbDBHelper.getInstance().save(reply);

                        MMSSender.getInstance().send(reply);
//                        SMSSender.sendReply(reply);
                    }
                });
            }
        });
    }
}
