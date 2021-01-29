package com.webapp.delavergy.services.api.fun;

import android.app.Activity;

import com.webapp.delavergy.R;
import com.webapp.delavergy.models.Privacy;
import com.webapp.delavergy.models.Result;
import com.webapp.delavergy.models.Settings;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.NetworkUtils;
import com.webapp.delavergy.utils.UIUtils;
import com.webapp.delavergy.utils.listener.RequestListener;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingData {

    public void getPrivacy(Activity activity, RequestListener<Privacy> requestListener) {
        if (NetworkUtils.checkTheInternet()) {
            AppController.getInstance().getClientAPI().getPrivacy()
                    .enqueue(new Callback<Privacy>() {
                        @Override
                        public void onResponse(Call<Privacy> call, Response<Privacy> response) {
                            if (response.isSuccessful()) {
                                requestListener.onSuccess(response.body(), response.message());
                            } else {
                                UIUtils.showError(activity, response.errorBody());
                            }
                        }

                        @Override
                        public void onFailure(Call<Privacy> call, Throwable t) {
                            requestListener.onFail(t.getLocalizedMessage());
                        }
                    });
        } else {
            UIUtils.showShortToast(activity, activity.getString(R.string.check_internet));
        }
    }

    public void getSettings(Activity activity, RequestListener<Settings> requestListener) {
        if (NetworkUtils.checkTheInternet()) {
            AppController.getInstance().getClientAPI().getSetting()
                    .enqueue(new Callback<Settings>() {
                        @Override
                        public void onResponse(Call<Settings> call, Response<Settings> response) {
                            if (response.isSuccessful()) {
                                requestListener.onSuccess(response.body(), response.message());
                            } else {
                                UIUtils.showError(activity, response.errorBody());
                            }
                        }

                        @Override
                        public void onFailure(Call<Settings> call, Throwable t) {
                            requestListener.onFail(t.getLocalizedMessage());
                        }
                    });
        } else {
            UIUtils.showShortToast(activity, activity.getString(R.string.check_internet));
        }
    }

    public void contact(Activity activity, Map<String, String> map, RequestListener<Result> requestListener) {
        if (NetworkUtils.checkTheInternet()) {
            AppController.getInstance().getClientAPI().contactUs(map)
                    .enqueue(new Callback<Result>() {
                        @Override
                        public void onResponse(Call<Result> call, Response<Result> response) {
                            if (response.isSuccessful()) {
                                requestListener.onSuccess(response.body(), response.message());
                            } else {
                                UIUtils.showError(activity, response.errorBody());
                            }
                        }

                        @Override
                        public void onFailure(Call<Result> call, Throwable t) {
                            requestListener.onFail(t.getLocalizedMessage());
                        }
                    });
        } else {
            UIUtils.showShortToast(activity, activity.getString(R.string.check_internet));
        }
    }
}
