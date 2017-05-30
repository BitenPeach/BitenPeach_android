package com.asuscomm.yangyinetwork.bitenpeach.utils;

import android.util.Log;

import com.asuscomm.yangyinetwork.bitenpeach.utils.consts.config.AllowedPhoneNumber;

/**
 * Created by jaeyoung on 2017. 5. 30..
 */

public class PhoneNumberChecker {
    private static final String TAG = "JYP/PhoneNumberChecker";

    public static boolean allowedPhoneNumber(String phoneNumber) {
        boolean result = false;

        for (String allowed:
        AllowedPhoneNumber.ALLOWED_PHONE_NUMBER) {
            if (allowed.equals(phoneNumber)) {
                Log.d(TAG, "allowedPhoneNumber: Allowed");
                result = true;
                break;
            }
        }

        return result;
    }
}
