package com.webapp.delavergy.services.api.fun;

import android.app.Activity;

import com.webapp.delavergy.models.Notifications;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.ToolUtils;
import com.webapp.delavergy.utils.listener.RequestListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationData {

    public void getNotification(Activity activity
            , RequestListener<Notifications> requestListener) {
        AppController.getInstance().getClientAPI().getNotification()
                .enqueue(new Callback<Notifications>() {
                    @Override
                    public void onResponse(Call<Notifications> call
                            , Response<Notifications> response) {
                        if (response.isSuccessful()) {
                            requestListener.onSuccess(response.body(), response.message());
                        } else {
                            ToolUtils.showError(activity, response.errorBody());
                        }
                    }

                    @Override
                    public void onFailure(Call<Notifications> call, Throwable t) {
                        requestListener.onFail(t.getLocalizedMessage());
                    }
                });
    }

    public void sendFcmToken(Activity activity, String token, RequestListener<String> requestListener) {
        AppController.getInstance().getClientAPI().sendFcmToken(token)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            requestListener.onSuccess(response.body(), response.message());
                        } else {
                            ToolUtils.showError(activity, response.errorBody());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        requestListener.onFail(t.getLocalizedMessage());
                    }
                });
    }
}
