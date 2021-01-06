package com.webapp.delavergy.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.webapp.delavergy.models.LoginResult;
import com.webapp.delavergy.models.User;

public class AppLocal {

    public final static String KEY_APP_LANGUAGE = "app_language";
    public final static String KEY_FIRST_RUN = "first_run";
    public final static String KEY_MOBILE = "mobile";

    public final static String KEY_USER = "user";
    public final static String KEY_LOGIN = "login";
    private final static String KEY_RESERVATION = "reservation";
    private final static String KEY_SUBSCRIBE = "subscribe";

    private final static String TRACKING_ORDER = "tracking";

    private static final String PREF_NAME = "AppLocal";
    private int PRIVATE_MODE = 0;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private Context context;

    public AppLocal(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstRun() {
        editor.putBoolean(KEY_FIRST_RUN, false);
        editor.apply();
    }

    public boolean isFirstRun() {
        return pref.getBoolean(KEY_FIRST_RUN, true);
    }

    public void setAppLanguage(String appLanguage) {
        editor.putString(KEY_APP_LANGUAGE, appLanguage);
        editor.apply();
    }

    public String getAppLanguage() {
        return pref.getString(KEY_APP_LANGUAGE, "ar");
    }

    public void setUser(LoginResult loginResult) {
        Gson gson = new Gson();
        String json = gson.toJson(loginResult);
        editor.putString(KEY_USER, json);
        editor.apply();
    }

    public LoginResult getUser() {
        Gson gson = new Gson();
        String json = pref.getString(KEY_USER, "");
        return gson.fromJson(json, LoginResult.class);
    }

    public void setLogin(boolean b) {
        editor.putBoolean(KEY_LOGIN, b);
        editor.apply();
    }

    public boolean isLogin() {
        return pref.getBoolean(KEY_LOGIN, false);
    }

    public void setMobile(String mobile) {
        editor.putString(KEY_MOBILE, mobile);
        editor.apply();
    }

    public String getMobile() {
        return pref.getString(KEY_MOBILE, "");
    }

    public void removeMobile() {
        editor.remove(KEY_MOBILE);
        editor.apply();
    }

    public void clean() {
        editor.clear();
        editor.apply();
    }
}
