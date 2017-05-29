package com.asuscomm.yangyinetwork.bitenpeach.utils.witai;

import android.util.Log;

import com.asuscomm.yangyinetwork.bitenpeach.models.domain.ProcessedText;
import com.asuscomm.yangyinetwork.bitenpeach.models.domain.witai.MeaningOfSentence;
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

    public void get(){
        String q = "$복숭아주문$ 서울시 동대문구 용두동 193-25번지로 복숭아 3만원 짜리 2박스 보내주세요. 받는사람은 김훈영이고 전화번호는 01041521166 입니다";

        Call<MeaningOfSentence> call = mNetwork.getMeaningOfSentence(authorization,
                q);

        call.enqueue(new Callback<MeaningOfSentence>() {
            @Override
            public void onResponse(Call<MeaningOfSentence> call, Response<MeaningOfSentence> response) {
                MeaningOfSentence res = response.body();
                Log.d(TAG, "get: "+response.body().toString());
                ProcessedText result = WitaiParser.witaiParser(res);
                Log.d(TAG, "ProcessedText: "+result.toString());
            }

            @Override
            public void onFailure(Call<MeaningOfSentence> call, Throwable t) {

            }
        });



    }
}
