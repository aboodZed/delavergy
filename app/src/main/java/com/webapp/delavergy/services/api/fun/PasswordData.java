package com.webapp.delavergy.services.api.fun;

import android.app.Activity;

import com.webapp.delavergy.R;
import com.webapp.delavergy.models.Code;
import com.webapp.delavergy.models.Nothing;
import com.webapp.delavergy.models.Result;
import com.webapp.delavergy.models.Verify;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.ToolUtils;
import com.webapp.delavergy.utils.listener.RequestListener;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordData {

    public void forgetPassword(Activity activity, String mobile, RequestListener<Code> requestListener) {
        if (ToolUtils.checkTheInternet()) {
            AppController.getInstance().getClientAPI().forgetPassword(mobile)
                    .enqueue(new Callback<Code>() {
                        @Override
                        public void onResponse(Call<Code> call, Response<Code> response) {
                            if (response.isSuccessful() && response.body().isSuccess()) {
                                requestListener.onSuccess(response.body(), response.message());
                            } else {
                                ToolUtils.showError(activity, response.errorBody());
                            }
                        }

                        @Override
                        public void onFailure(Call<Code> call, Throwable t) {
                            requestListener.onFail(t.getLocalizedMessage());
                        }
                    });
        } else {
            ToolUtils.showShortToast(activity, activity.getString(R.string.check_internet));
        }
    }

    public void verifyCode(Activity activity, String mobile, String code, RequestListener<Verify> requestListener) {
        if (ToolUtils.checkTheInternet()) {
            AppController.getInstance().getClientAPI().verifyCode(mobile, code)
                    .enqueue(new Callback<Verify>() {
                        @Override
                        public void onResponse(Call<Verify> call, Response<Verify> response) {
                            if (response.isSuccessful() && response.body().isSuccess()) {
                                requestListener.onSuccess(response.body(), response.message());
                            } else {
                                ToolUtils.showError(activity, response.errorBody());
                            }
                        }

                        @Override
                        public void onFailure(Call<Verify> call, Throwable t) {
                            requestListener.onFail(t.getLocalizedMessage());
                        }
                    });
        } else {
            ToolUtils.showShortToast(activity, activity.getString(R.string.check_internet));
        }
    }

    public void resetPassword(Activity activity, String password, RequestListener<Result> requestListener) {
        if (ToolUtils.checkTheInternet()) {
            AppController.getInstance().getClientAPI().resetPassword(password, password)
                    .enqueue(new Callback<Result>() {
                        @Override
                        public void onResponse(Call<Result> call, Response<Result> response) {
                            if (response.isSuccessful() && response.body().isSuccess()) {
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

}
