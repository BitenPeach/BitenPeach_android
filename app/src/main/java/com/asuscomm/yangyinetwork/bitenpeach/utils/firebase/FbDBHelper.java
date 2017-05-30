package com.asuscomm.yangyinetwork.bitenpeach.utils.firebase;

import android.util.Log;

/**
 * Created by jaeyoung on 2017. 5. 30..
 */

public class FbDBHelper {
    private final String TAG = "JYP/"+getClass().getSimpleName();
    private static FbDBHelper mInstance = null;

    public static FbDBHelper getInstance() {
        if (mInstance == null) {
            mInstance = new FbDBHelper();
        }
        return mInstance;
    }
    public void save(Object object) {
        Log.d(TAG, "save: object="+object.toString());
    }
}
