package com.webapp.delavergy.utils;

import android.app.Activity;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

import com.webapp.delavergy.R;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.ResponseBody;

public class UIUtils {

    public static void showLongToast(Activity activity, String s) {
        Toast.makeText(activity, s, Toast.LENGTH_LONG).show();
    }

    public static void showShortToast(Activity activity, String s) {
        Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
    }


    public static void showError(Activity activity, ResponseBody s) {
        try {
            Log.e("errorInNoSuccess", s.string());
            //JSONObject jObjError = new JSONObject(s.string()).getJSONObject("status");
            showLongToast(activity, new JSONObject(s.string()).getString("message"));
        } catch (Exception e) {
            if (activity != null)
                showLongToast(activity, activity.getString(R.string.error));
        }
    }


    public static String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
        return date;
    }

    public static String getTime(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("hh:mm", cal).toString();
        return date;
    }

    public static String formatTime(int i) {
        String x;
        if (i < 10) {
            x = "0" + i;
        } else {
            x = "" + i;
        }
        return x;
    }
}
