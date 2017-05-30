package com.asuscomm.yangyinetwork.bitenpeach.utils.sms;

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
import com.asuscomm.yangyinetwork.bitenpeach.utils.AppController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaeyoung on 2017. 5. 30..
 */

public class SMSSender {
    private static final String TAG = "JYP/SMSSender";
    private static final int SMS_LIMIT = 70;

    public static void sendReply(Reply reply) {
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

        SmsManager smsManager = SmsManager.getDefault();

        List<String> textsForSMS = cutTextForSMS(reply.getBody());

        for (String text:
             textsForSMS) {
            smsManager.sendTextMessage(reply.getPhoneNumber(), null, text, sentIntent, deliveredIntent);
            Log.d(TAG, "sendReply: text="+text);
        }
    }

    public static List<String> cutTextForSMS(String text) {
        List<String> texts = new ArrayList<>();
        int cursor = 0;

        while(text.length() > SMS_LIMIT+cursor) {
            String sub = text.substring(cursor, cursor+SMS_LIMIT);
            Log.d(TAG, "cutTextForSMS: substring="+sub);
            texts.add(sub);
            cursor += SMS_LIMIT;
        }

        String last = text.substring(cursor, text.length());
        Log.d(TAG, "cutTextForSMS: laststring="+last);
        texts.add(last);

        return texts;
    }
}
