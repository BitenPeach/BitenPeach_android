package com.asuscomm.yangyinetwork.bitenpeach.utils.witai.network;

import com.asuscomm.yangyinetwork.bitenpeach.models.witai.MeaningOfSentence;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by jaeyoung on 2017. 4. 19..
 */

public interface WitaiNetwork {
    @GET("message")
    Call<MeaningOfSentence> getMeaningOfSentence(@Header("Authorization") String authorization,
                                                 @Query("q") String q);
}
