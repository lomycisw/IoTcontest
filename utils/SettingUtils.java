package com.example.f_masa.proximityareadetection.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.f_masa.proximityareadetection.Authenticator;

/**
 * Created by f-masa on 2017/01/11.
 */

public class SettingUtils {
    public static final String ACCOUNT_FILE_NAME = "ACCOUNT";
    public static final String UTILS_CODE = "CODE";
    public static final String UTILS_UUID = "UUID";
    public static final String UTILS_SECRET = "SECRET";
    public static final String UTILS_AUTH = "isAuth";

    public static void preferenceRegister(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(ACCOUNT_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(UTILS_CODE, Authenticator.getInstance().getCode());
        editor.putString(UTILS_SECRET, Authenticator.getInstance().getSecret());
        editor.putString(UTILS_UUID, Authenticator.getInstance().getUuid());
        editor.putBoolean(UTILS_AUTH, Authenticator.getInstance().isAuth());
        editor.apply();
    }

    public static void getPreferences(Context context){
        SharedPreferences preferences = context.getSharedPreferences(ACCOUNT_FILE_NAME, Context.MODE_PRIVATE);

        Authenticator.getInstance().setCode(preferences.getString(UTILS_CODE,Authenticator.getInstance().getCode()));
        Authenticator.getInstance().setSecret(preferences.getString(UTILS_SECRET,Authenticator.getInstance().getSecret()));
        Authenticator.getInstance().setUuid(preferences.getString(UTILS_UUID,Authenticator.getInstance().getUuid()));
        Authenticator.getInstance().setAuth(preferences.getBoolean(UTILS_AUTH,Authenticator.getInstance().isAuth()));

    }
}
