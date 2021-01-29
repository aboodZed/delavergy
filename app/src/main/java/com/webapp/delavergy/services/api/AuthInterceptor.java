package com.webapp.delavergy.services.api;

import android.content.Context;

import androidx.annotation.NonNull;

import com.webapp.delavergy.feature.login.LoginActivity;
import com.webapp.delavergy.utils.AppContent;
import com.webapp.delavergy.utils.NavigateUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private Context context;

    public AuthInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        Response response = chain.proceed(builder.build());
        if (response.code() == 401) {
            NavigateUtils.activityIntent(context, LoginActivity.class, true);
        }
        return response;
    }
}