package com.asuscomm.yangyinetwork.bitenpeach.utils;

import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.asuscomm.yangyinetwork.bitenpeach.models.domain.RawText;
import com.asuscomm.yangyinetwork.bitenpeach.utils.mms.MMSObserver;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jaeyoung on 2017. 4. 7..
 */

public class AppController extends Application implements ValueEventListener {
    private static final String TAG = "JYP/"+"AppController";

    private static final String refName = "rawTexts";
    private static boolean SMS_RECEIVE_FLAG = true;
    private static DatabaseReference rawTextRef;

    private static AppController mInstance;

    public static AppController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initFirebaseDatabase();
        initMMSObserver();

        rawTextRef.addValueEventListener(this);

        Log.d(TAG, "onCreate: ");
    }

    public static boolean SMS_RECEIVE() {
        return SMS_RECEIVE_FLAG;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "onTerminate: ");
        rawTextRef = null;
    }

    public static void saveTextMsg(String phoneNumber, String messageBody) {
        RawText rawText = new RawText(phoneNumber, messageBody);

        if(rawTextRef == null) {
            initFirebaseDatabase();
        }

        String key = rawTextRef.push().getKey();


        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(key, rawText);

        rawTextRef.updateChildren(childUpdates);
    }

    private static void initFirebaseDatabase() {
        Log.d(TAG, "initFirebaseDatabase: ");
        rawTextRef = FirebaseDatabase.getInstance().getReference(refName);
    }

    private void initMMSObserver() {
        Log.d(TAG, "initMMSObserver: ");
        MMSObserver.create();
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        RawText rawText = dataSnapshot.getValue(RawText.class);
        Log.d(TAG, "Value is: " + rawText);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        Log.w(TAG, "Failed to read value.", databaseError.toException());
    }

    public void chkPermission(String permission, Activity activity) {
        Log.d(TAG, "chkPermission: ");
        int permissionCheck = ContextCompat.checkSelfPermission(activity, permission);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "chkPermission: Granted");
            return;
        } else {
            Log.d(TAG, "chkPermission: GettingPermission");
            String[] permissions = { permission };
            ActivityCompat.requestPermissions(activity, permissions, 1);
        }
    }

}