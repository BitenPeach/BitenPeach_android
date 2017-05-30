package com.asuscomm.yangyinetwork.bitenpeach.utils.mms;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.asuscomm.yangyinetwork.bitenpeach.models.domain.RawText;
import com.asuscomm.yangyinetwork.bitenpeach.models.logic.RawTextProcesser;
import com.asuscomm.yangyinetwork.bitenpeach.utils.AppController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.asuscomm.yangyinetwork.bitenpeach.utils.sms.SMSReceiver.processSMS;

/**
 * Created by jaeyoung on 2017. 5. 30..
 */

public class MMSReceiver {
    private final String TAG = "JYP/"+getClass().getSimpleName();
    public static MMSReceiver mInstance = null;
    private ContentResolver contentResolver;
    private List<String> existMMSList;
    private boolean processing = false;



    public static MMSReceiver getInstance() {
        if (mInstance == null) {
            mInstance = create();
        }
        return mInstance;
    }

    public static MMSReceiver create() {
        MMSReceiver mmsReceiver = new MMSReceiver();
        mmsReceiver.contentResolver = AppController.getInstance().getContentResolver();

        mmsReceiver.initExistMMSList();

        return mmsReceiver;
    }

    private void initExistMMSList() {
        existMMSList = new ArrayList<>();
        final String[] projection = new String[]{"_id"};
        Uri uri = Uri.parse("content://mms/inbox");
        Cursor query = contentResolver.query(uri, projection, null, null, "date DESC");

        if (query.moveToFirst()) {
            do {
                String mmsId = query.getString(query.getColumnIndex("_id"));
                existMMSList.add(mmsId);
            } while (query.moveToNext());
        }
        query.close();
    }

    public void getMMS() {
        if(!processing) {
            processing = true;

            final String[] projection = new String[]{"_id", "ct_t", "date"};
            Uri uri = Uri.parse("content://mms/inbox");
            Cursor query = contentResolver.query(uri, projection, null, null, "date DESC");

            if (query.moveToFirst()) {
                do {
                    String mmsId = query.getString(query.getColumnIndex("_id"));
                    if (!existMMSList.contains(mmsId)) {
                        getMMS(mmsId);
                    }
                } while (query.moveToNext());

            }
            query.close();
            processing = false;
        } else {
            Log.d(TAG, "getMMS: prossing");
        }
    }

    private void getMMS(String mmsId) {
        String incommingNumber = getANumber(Integer.parseInt(mmsId));
        String messageBody = "";
        String selectionPart = "mid=" + mmsId;
        Uri uriPart = Uri.parse("content://mms/part");
        Cursor cur = contentResolver.query(uriPart, null, selectionPart, null, null);
        if (cur.moveToFirst()) {
            do {
                String partId = cur.getString(cur.getColumnIndex("_id"));
                String type = cur.getString(cur.getColumnIndex("ct"));
                if ("text/plain".equals(type)) {
                    String data = cur.getString(cur.getColumnIndex("_data"));

                    if (data != null) {
                        messageBody = getMmsText(partId);
                    } else {
                        messageBody = cur.getString(cur.getColumnIndex("text"));
                    }
                    if (incommingNumber.length() > 0 && messageBody.length() > 0) {
                        existMMSList.add(mmsId);
                    }
                }

            } while (cur.moveToNext());

            cur.close();
        }

        Log.d(TAG, "getMMS: "+"From : "+incommingNumber +" Body : "+messageBody);
        processSMS(incommingNumber, messageBody);
    }

    /**
     * Get Sender number
     *
     * @param id
     * @return
     */
    private String getANumber(int id) {
        String add = "";
        final String[] projection = new String[] {"address","contact_id","charset","type"};
        Uri.Builder builder = Uri.parse("content://mms").buildUpon();
        builder.appendPath(String.valueOf(id)).appendPath("addr");
        Cursor cursor = AppController.getInstance().getContentResolver().query(
                builder.build(),
                projection,
                null,
                null, null);
        if (cursor.moveToFirst()) {
            add = cursor.getString(cursor.getColumnIndex("address"));
        }
        return add;
    }

    /**
     * Get MMS text content
     *
     * @param id
     * @return
     */
    private String getMmsText(String id) {
        Uri partURI = Uri.parse("content://mms/part/" + id);
        InputStream is = null;
        StringBuilder sb = new StringBuilder();
        try {
            is = AppController.getInstance().getContentResolver().openInputStream(partURI);
            if (is != null) {
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                BufferedReader reader = new BufferedReader(isr);
                String temp = reader.readLine();
                while (temp != null) {
                    sb.append(temp);
                    temp = reader.readLine();
                }
            }
        } catch (IOException e) {}
        finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {}
            }
        }
        return sb.toString();
    }
}
