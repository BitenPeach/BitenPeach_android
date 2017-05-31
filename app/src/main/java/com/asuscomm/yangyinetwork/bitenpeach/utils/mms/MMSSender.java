package com.asuscomm.yangyinetwork.bitenpeach.utils.mms;

import android.util.Log;

import com.asuscomm.yangyinetwork.bitenpeach.models.domain.Reply;
import com.asuscomm.yangyinetwork.bitenpeach.utils.AppController;
import com.klinker.android.send_message.Message;
import com.klinker.android.send_message.Settings;
import com.klinker.android.send_message.Transaction;

/**
 * Created by jaeyoung on 2017. 5. 31..
 */
//  i like android 저 없어요 아이폰 ㅋㅋㅋ
public class MMSSender {
    private final String TAG = "JYP/"+getClass().getSimpleName();
    private static MMSSender mInstance;

    public static MMSSender getInstance() {
        if (mInstance == null) {
            mInstance = new MMSSender();
        }
        return mInstance;
    }

    public void send(Reply reply) {
        send(reply.getPhoneNumber(), reply.getBody());
    }

    public void send(String phoneNumber, String body) {
        Log.d(TAG, "send: phoneNumber="+phoneNumber+"body="+body);
        Settings settings = new Settings();
        settings.setUseSystemSending(true);
        Transaction transaction = new Transaction(AppController.getInstance(), settings);
        Message message = new Message(body, phoneNumber);
        message.setSubject("title");
        Log.d(TAG, "send: getText="+message.getText());
        Log.d(TAG, "send: getDelay="+message.getDelay());
        Log.d(TAG, "send: getSubject="+message.getSubject());

        transaction.sendNewMessage(message, Transaction.NO_THREAD_ID);
    }

}
