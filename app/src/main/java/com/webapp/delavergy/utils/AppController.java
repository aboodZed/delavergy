package com.webapp.delavergy.utils;

import android.app.Application;

import com.webapp.delavergy.services.api.ApiClient;
import com.webapp.delavergy.services.api.ApiInterface;
import com.webapp.delavergy.services.api.GetAPIData;

public class AppController extends Application {

    private static AppController mInstance;

    private AppLocal appLocal;
    private GetAPIData getAPIData;
    private ApiInterface clientAPI;

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        appLocal = new AppLocal(this);
        getAPIData = new GetAPIData();
        clientAPI = ApiClient.getRetrofit().create(ApiInterface.class);
    }

    public AppLocal getAppLocal() {
        return appLocal;
    }

    public ApiInterface getClientAPI() {
        return clientAPI;
    }

    public GetAPIData getGetAPIData() {
        return getAPIData;
    }
}
