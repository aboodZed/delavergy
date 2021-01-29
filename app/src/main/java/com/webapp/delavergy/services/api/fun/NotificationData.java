package com.webapp.delavergy.services.api.fun;

import android.app.Activity;

import com.webapp.delavergy.R;
import com.webapp.delavergy.models.Notifications;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.NetworkUtils;
import com.webapp.delavergy.utils.UIUtils;
import com.webapp.delavergy.utils.listener.RequestListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationData {

    public void getNotification(Activity activity
            , RequestListener<Notifications> requestListener) {
        if (NetworkUtils.checkTheInternet()) {
            AppController.getInstance().getClientAPI().getNotification()
                    .enqueue(new Callback<Notifications>() {
                        @Override
                        public void onResponse(Call<Notifications> call
                                , Response<Notifications> response) {
                            if (response.isSuccessful()) {
                                requestListener.onSuccess(response.body(), response.message());
                            } else {
                                UIUtils.showError(activity, response.errorBody());
                            }
                        }

                        @Override
                        public void onFailure(Call<Notifications> call, Throwable t) {
                            requestListener.onFail(t.getLocalizedMessage());
                        }
                    });
        } else {
            UIUtils.showShortToast(activity, activity.getString(R.string.check_internet));
        }
    }
}
