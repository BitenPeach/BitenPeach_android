package com.asuscomm.yangyinetwork.bitenpeach.utils;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.util.Log;

import com.asuscomm.yangyinetwork.bitenpeach.models.domain.Reply;

/**
 * Created by jaeyoung on 2017. 5. 30..
 */

public class SMSSender {
    private static final String TAG = "JYP/SMSSender";
    public static void sendReply(Reply reply, Activity activity) {
        Log.d(TAG, "sendReply: reply="+reply.toString());

        Context appControllerContext = AppController.getInstance();

        PendingIntent sentIntent = PendingIntent.getBroadcast(appControllerContext, 0, new Intent("SMS_SENT_ACTION"), 0);
        PendingIntent deliveredIntent = PendingIntent.getBroadcast(appControllerContext, 0, new Intent("SMS_DELIVERED_ACTION"), 0);

        appControllerContext.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context appControllerContext, Intent intent) {
                Log.d(TAG, "onReceive: intent=SMS_SENT_ACTION resultCode="+getResultCode());
            }
        }, new IntentFilter("SMS_SENT_ACTION"));

        appControllerContext.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context appControllerContext, Intent intent) {
                Log.d(TAG, "onReceive: intent=SMS_DELIVERED_ACTION resultCode="+getResultCode());
            }
        }, new IntentFilter("SMS_DELIVERED_ACTION"));

        AppController.getInstance().chkPermission(Manifest.permission.SEND_SMS, activity);

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(reply.getPhoneNumber(), null, reply.getBody(), sentIntent, deliveredIntent);
    }
}
