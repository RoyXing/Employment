package com.employment.utils;


import android.content.Context;
import android.content.SharedPreferences;

import com.employment.app.App;
import com.employment.app.Constants;

/**
 * Created by roy on 2017/3/28.
 */

public class SharedPreferenceUtil {

    private static final String SHAREDPREFERENCES_NAME = "my_sp";


    public static SharedPreferences getAppSp() {
        return App.getInstance().getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static void setUserStatus(int status) {
        getAppSp().edit().putInt(Constants.USER_STATUS, status).apply();
    }

    public static int getUserStatud() {
        return getAppSp().getInt(Constants.USER_STATUS, 0);
    }

    public static void setIsLogin(boolean isLogin) {
        getAppSp().edit().putBoolean(Constants.KEY_IS_LOGIN, isLogin).apply();
    }

    public static boolean getIsLogin() {
        return getAppSp().getBoolean(Constants.KEY_IS_LOGIN, false);
    }
}
