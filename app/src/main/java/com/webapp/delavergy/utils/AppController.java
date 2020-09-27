package com.webapp.delavergy.utils;

import android.app.Application;

import com.webapp.delavergy.services.api.ApiClient;
import com.webapp.delavergy.services.api.ApiInterface;
import com.webapp.delavergy.services.api.ApiShared;

public class AppController extends Application {

    private static AppController mInstance;

    private AppLocal appLocal;
    private ApiInterface clientAPI;
    private ApiShared apiShared;

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        appLocal = new AppLocal(this);
        //clientAPI = ApiClient.getRetrofit().create(ApiInterface.class);
        apiShared = new ApiShared();
    }

    public AppLocal getAppLocal() {
        return appLocal;
    }

    public ApiInterface getClientAPI() {
        return clientAPI;
    }

    public ApiShared getApiShared() {
        return apiShared;
    }
}
