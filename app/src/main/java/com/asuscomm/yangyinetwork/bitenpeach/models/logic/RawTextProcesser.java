package com.asuscomm.yangyinetwork.bitenpeach.models.logic;

import android.app.Activity;
import android.util.Log;

import com.asuscomm.yangyinetwork.bitenpeach.models.domain.OrderSheet;
import com.asuscomm.yangyinetwork.bitenpeach.models.domain.ProcessedText;
import com.asuscomm.yangyinetwork.bitenpeach.models.domain.RawText;
import com.asuscomm.yangyinetwork.bitenpeach.models.domain.Reply;
import com.asuscomm.yangyinetwork.bitenpeach.models.domain.witai.MeaningOfSentence;
import com.asuscomm.yangyinetwork.bitenpeach.models.logic.witai.WitaiParser;
import com.asuscomm.yangyinetwork.bitenpeach.utils.SMSSender;
import com.asuscomm.yangyinetwork.bitenpeach.utils.witai.WitaiService;

/**
 * Created by jaeyoung on 2017. 5. 30..
 */

public class RawTextProcesser {
    private static final String TAG = "JYP/RawTextProcesser";

    public static void processRawText(final RawText rawText, final Activity activity) {
        WitaiService.getInstance().get(rawText, new WitaiService.OnSuccessListener() {
            @Override
            public void onSuccess(MeaningOfSentence meaningOfSentence) {
                Log.d(TAG, "onResponse: meaningOfSentence="+meaningOfSentence.toString());
                ProcessedText processedText = WitaiParser.witaiParser(meaningOfSentence);
                Log.d(TAG, "onResponse: processedText="+processedText.toString());
                processedText.setPhoneNumber(rawText.getPhoneNumber());
                OrderSheet orderSheet = OrderSheetFiller.fillOrderSheet(processedText);
                Log.d(TAG, "onResponse: orderSheet="+orderSheet.toString());
                Reply reply = ReplyMaker.makeReply(orderSheet);
                Log.d(TAG, "onResponse: reply="+reply.toString());

//                SMSSender.sendReply(reply, activity);
            }
        });
    }
}
