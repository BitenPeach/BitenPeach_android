package com.asuscomm.yangyinetwork.bitenpeach.utils.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.asuscomm.yangyinetwork.bitenpeach.models.domain.RawText;
import com.asuscomm.yangyinetwork.bitenpeach.utils.AppController;
import com.asuscomm.yangyinetwork.bitenpeach.utils.PhoneNumberChecker;

import static com.asuscomm.yangyinetwork.bitenpeach.models.logic.RawTextProcesser.processRawText;

/**
 * Created by jaeyoung on 2017. 4. 7..
 */

public class SMSReceiver extends BroadcastReceiver {
    private final String TAG = "JYP/"+getClass().getSimpleName();

    private final String SMSReceiveAction = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: ");

        if (!AppController.SMS_RECEIVE())
            return;
        if (SMSReceiveAction.equals(intent.getAction())) {
            Bundle bundle = intent.getExtras();
            Object[] messages = (Object[])bundle.get("pdus");

            for (Object message:messages) {
                SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) message);
                String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                String messageBody = currentMessage.getDisplayMessageBody();
                Log.d(TAG, "onReceive: "+"From : "+phoneNumber +" Body : "+messageBody);

                processSMS(phoneNumber, messageBody);
            }
        }
    }

    private void processSMS(String phoneNumber, String messageBody) {
        if (PhoneNumberChecker.allowedPhoneNumber(phoneNumber)) {
            Log.i(TAG, "processSMS: Allowed PhoneNumber");
            RawText rawText = new RawText(phoneNumber, messageBody);
            processRawText(rawText);
        } else {
            Log.i(TAG, "processSMS: Disallowed PhoneNumber");
        }
    }
}