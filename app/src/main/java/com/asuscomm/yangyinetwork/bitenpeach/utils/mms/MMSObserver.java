package com.asuscomm.yangyinetwork.bitenpeach.utils.mms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import com.asuscomm.yangyinetwork.bitenpeach.utils.AppController;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import static com.asuscomm.yangyinetwork.bitenpeach.utils.consts.config.MMSObserver.MMSOBSERVER_ONFINISH_TIME_IN_MIL;
import static com.asuscomm.yangyinetwork.bitenpeach.utils.consts.config.MMSObserver.MMSOBSERVER_TIMER_SLEEP_TIME_IN_MIL;


/**
 * Created by jaeyoung on 2017. 5. 30..
 */

public class MMSObserver extends ContentObserver {
    private final String TAG = "JYP/"+getClass().getSimpleName();

    private AtomicLong startTime;
    private AtomicBoolean timerOn;

    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public MMSObserver(Handler handler) {
        super(handler);
        AppController.getInstance().getContentResolver().registerContentObserver (Uri.parse("content://mms"), true, this);
    }

    public static MMSObserver create() {
        Handler handler = new Handler();
        MMSObserver mmsObserver = new MMSObserver(handler);
        mmsObserver.initTimer();

        return mmsObserver;
    }

    private void initTimer() {
        timerOn = new AtomicBoolean();
        startTime = new AtomicLong();
        setTimerOn(false);
    }

    public void setTimerOn(boolean timerOn) {
        Log.d(TAG, "setTimerOn: timerOn="+timerOn);
        this.timerOn.set(timerOn);
    }

    private Thread createTimer() {
        Runnable timer_runnable = new Runnable() {
            @Override
            public void run() {
                while(true) {
                    Calendar c = Calendar.getInstance();
                    long now = c.getTimeInMillis();

                    Log.d(TAG, "run: (now-startTime)="+(now-startTime.get())+"MMSOBSERVER_ONFINISH_TIME_IN_MIL="+MMSOBSERVER_ONFINISH_TIME_IN_MIL);
                    if((now-startTime.get()) > MMSOBSERVER_ONFINISH_TIME_IN_MIL) {
                        break;
                    }

                    try {
                        Thread.sleep(MMSOBSERVER_TIMER_SLEEP_TIME_IN_MIL);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                timerOnFinish();
            }
        };

        Thread timer = new Thread(timer_runnable);

        return timer;
    }

    private void timerOnFinish() {
        setTimerOn(false);
        MMSReceiver.getInstance().getMMS();
        Log.d(TAG, "run: end");
    }

    private void setTimer() {
        if(!timerOn.get()) {
            setTimerOn(true);
            Calendar c = Calendar.getInstance();
            double now = c.getTimeInMillis();
            setStartTime((long) now);

            createTimer().start();
            Log.d(TAG, "setTimer: timerOff -> run");
        } else {
            Calendar c = Calendar.getInstance();
            double now = c.getTimeInMillis();
            setStartTime((long) now);
            Log.d(TAG, "setTimer: timerOn -> skip");
        }
        Log.d(TAG, "setTimer: timerOn="+timerOn.get());
    }

    public void setStartTime(long startTime) {
        Log.d(TAG, "setStartTime: startTime="+startTime);
        this.startTime.set(startTime);
    }

    @Override
    public void onChange(boolean selfChange) {
        Log.d(TAG, "onChange: ");
        setTimer();

        super.onChange(selfChange);
    }
}
