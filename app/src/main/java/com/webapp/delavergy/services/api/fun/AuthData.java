package com.webapp.delavergy.services.api.fun;

import android.app.Activity;

import com.webapp.delavergy.R;
import com.webapp.delavergy.models.LoginResult;
import com.webapp.delavergy.models.Result;
import com.webapp.delavergy.models.User;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.ToolUtils;
import com.webapp.delavergy.utils.dialog.WaitDialogFragment;
import com.webapp.delavergy.utils.listener.RequestListener;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthData {

    public void login(Activity activity, Map<String, String> map, RequestListener<LoginResult> requestListener) {
        if (ToolUtils.checkTheInternet()) {
            AppController.getInstance().getClientAPI().login(map).enqueue(new Callback<LoginResult>() {
                @Override
                public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                    if (response.isSuccessful() && response.body().isSuccess()) {
                        requestListener.onSuccess(response.body(), response.message());
                    } else {
                        ToolUtils.showError(activity, response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<LoginResult> call, Throwable t) {
                    requestListener.onFail(t.getLocalizedMessage());
                }
            });
        } else {
            ToolUtils.showShortToast(activity, activity.getString(R.string.check_internet));
        }
    }

    public void changeStatus(Activity activity, int status, RequestListener<Result> requestListener) {
        if (ToolUtils.checkTheInternet()) {
            AppController.getInstance().getClientAPI().setOnline(status)
                    .enqueue(new Callback<Result>() {
                        @Override
                        public void onResponse(Call<Result> call, Response<Result> response) {
                            if (response.isSuccessful()) {
                                requestListener.onSuccess(response.body(), response.message());
                            } else {
                                ToolUtils.showError(activity, response.errorBody());
                            }
                        }

                        @Override
                        public void onFailure(Call<Result> call, Throwable t) {
                            requestListener.onFail(t.getLocalizedMessage());
                        }
                    });
        } else {
            ToolUtils.showShortToast(activity, activity.getString(R.string.check_internet));
        }
    }

    public void getProfile(Activity activity, RequestListener<User> requestListener) {
        if (ToolUtils.checkTheInternet()) {
            AppController.getInstance().getClientAPI().getProfile()
                    .enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (response.isSuccessful()) {
                                requestListener.onSuccess(response.body(), response.message());
                            } else {
                                ToolUtils.showError(activity, response.errorBody());
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            requestListener.onFail(t.getLocalizedMessage());
                        }
                    });
        } else {
            ToolUtils.showShortToast(activity, activity.getString(R.string.check_internet));
        }
    }
}
