package com.asuscomm.yangyinetwork.bitenpeach.utils.mms;

import android.util.Log;

import com.asuscomm.yangyinetwork.bitenpeach.utils.AppController;
import com.klinker.android.send_message.Message;
import com.klinker.android.send_message.Settings;
import com.klinker.android.send_message.Transaction;

/**
 * Created by jaeyoung on 2017. 5. 31..
 */

public class MMSSender {
    private final String TAG = "JYP/"+getClass().getSimpleName();
    private static MMSSender mInstance;

    public static MMSSender getInstance() {
        if (mInstance == null) {
            mInstance = new MMSSender();
        }
        return mInstance;
    }

    public void send(String phoneNumber, String body) {
        Log.d(TAG, "send: ");
        Settings settings = new Settings();
        settings.setUseSystemSending(true);
        Transaction transaction = new Transaction(AppController.getInstance(), settings);
        Message message = new Message(body, phoneNumber);
        transaction.sendNewMessage(message, Transaction.NO_THREAD_ID);
    }

}
