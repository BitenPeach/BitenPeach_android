package com.asuscomm.yangyinetwork.bitenpeach.utils.witai;

import android.util.Log;

import com.asuscomm.yangyinetwork.bitenpeach.models.domain.OrderSheet;
import com.asuscomm.yangyinetwork.bitenpeach.models.domain.ProcessedText;
import com.asuscomm.yangyinetwork.bitenpeach.models.domain.RawText;
import com.asuscomm.yangyinetwork.bitenpeach.models.domain.Reply;
import com.asuscomm.yangyinetwork.bitenpeach.models.domain.witai.MeaningOfSentence;
import com.asuscomm.yangyinetwork.bitenpeach.models.logic.OrderSheetFiller;
import com.asuscomm.yangyinetwork.bitenpeach.models.logic.ReplyMaker;
import com.asuscomm.yangyinetwork.bitenpeach.models.logic.witai.WitaiParser;
import com.asuscomm.yangyinetwork.bitenpeach.utils.witai.network.WitaiNetwork;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jaeyoung on 2017. 4. 19..
 */

public class WitaiService {
    private static WitaiService mInstance = null;
    String TAG = "JYP/"+getClass().getSimpleName();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.wit.ai/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    WitaiNetwork mNetwork = retrofit.create(WitaiNetwork.class);
    String token = "CP725AEXHMNBQ5IUBIBNVNH735IFKLYV";
    String authorization = "Bearer " + token;

    public WitaiService() {
    }

    public static WitaiService getInstance() {
        if(mInstance == null) {
            mInstance = new WitaiService();
        }
        return mInstance;
    }

    public interface OnSuccessListener {
        void onSuccess(MeaningOfSentence result);
    }

    public void get(final RawText rawText, final OnSuccessListener listener){
        String q = rawText.getMessageBody();

        Call<MeaningOfSentence> call = mNetwork.getMeaningOfSentence(authorization,
                q);

        call.enqueue(new Callback<MeaningOfSentence>() {
            @Override
            public void onResponse(Call<MeaningOfSentence> call, Response<MeaningOfSentence> response) {
                listener.onSuccess(response.body());
//                MeaningOfSentence meaningOfSentence= response.body();
//                Log.d(TAG, "onResponse: meaningOfSentence="+meaningOfSentence.toString());
//                ProcessedText processedText = WitaiParser.witaiParser(meaningOfSentence);
//                Log.d(TAG, "onResponse: processedText="+processedText.toString());
//                processedText.setPhoneNumber(rawText.getPhoneNumber());
//                OrderSheet orderSheet = OrderSheetFiller.fillOrderSheet(processedText);
//                Log.d(TAG, "onResponse: orderSheet="+orderSheet.toString());
//                Reply reply = ReplyMaker.makeReply(orderSheet);
//                Log.d(TAG, "onResponse: reply="+reply.toString());
            }

            @Override
            public void onFailure(Call<MeaningOfSentence> call, Throwable t) {

            }
        });



    }
}
