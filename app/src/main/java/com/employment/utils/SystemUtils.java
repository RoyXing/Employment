package com.employment.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.employment.app.App;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by roy on 2017/4/7.
 */

public class SystemUtils {
    /**
     * 检查是否有可用网络
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 将时间戳转换为时间
     */
    public static String stampToDate(long s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(s);
        res = simpleDateFormat.format(date);
        return res;
    }
}
