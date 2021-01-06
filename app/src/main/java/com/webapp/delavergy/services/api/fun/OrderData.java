package com.webapp.delavergy.services.api.fun;

import android.app.Activity;

import com.webapp.delavergy.R;
import com.webapp.delavergy.models.Orders;
import com.webapp.delavergy.models.Wallets;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.ToolUtils;
import com.webapp.delavergy.utils.listener.RequestListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderData {

    public void getOrders(Activity activity, RequestListener<Orders> requestListener) {
        if (ToolUtils.checkTheInternet()) {
            AppController.getInstance().getClientAPI().getOrders()
                    .enqueue(new Callback<Orders>() {
                        @Override
                        public void onResponse(Call<Orders> call, Response<Orders> response) {
                            if (response.isSuccessful()) {
                                requestListener.onSuccess(response.body(), response.message());
                            } else {
                                ToolUtils.showError(activity, response.errorBody());
                            }
                        }

                        @Override
                        public void onFailure(Call<Orders> call, Throwable t) {
                            requestListener.onFail(t.getLocalizedMessage());
                        }
                    });
        } else {
            ToolUtils.showShortToast(activity, activity.getString(R.string.check_internet));
        }
    }

    public void getWallets(Activity activity, RequestListener<Wallets> requestListener) {
        if (ToolUtils.checkTheInternet()) {
            AppController.getInstance().getClientAPI().getWallets()
                    .enqueue(new Callback<Wallets>() {
                        @Override
                        public void onResponse(Call<Wallets> call, Response<Wallets> response) {
                            if (response.isSuccessful()) {
                                requestListener.onSuccess(response.body(), response.message());
                            } else {
                                ToolUtils.showError(activity, response.errorBody());
                            }
                        }

                        @Override
                        public void onFailure(Call<Wallets> call, Throwable t) {
                            requestListener.onFail(t.getLocalizedMessage());
                        }
                    });
        } else {
            ToolUtils.showShortToast(activity, activity.getString(R.string.check_internet));
        }
    }
}
