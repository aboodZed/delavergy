package com.webapp.delavergy.services.api.fun;

import android.app.Activity;

import com.webapp.delavergy.R;
import com.webapp.delavergy.models.Order;
import com.webapp.delavergy.models.Orders;
import com.webapp.delavergy.models.Result;
import com.webapp.delavergy.models.Wallets;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.NetworkUtils;
import com.webapp.delavergy.utils.UIUtils;
import com.webapp.delavergy.utils.listener.RequestListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderData {

    /*public void getOrders(Activity activity, RequestListener<Orders> requestListener) {
        if (NetworkUtils.checkTheInternet()) {
            AppController.getInstance().getClientAPI().getOrders()
                    .enqueue(new Callback<Orders>() {
                        @Override
                        public void onResponse(Call<Orders> call, Response<Orders> response) {
                            if (response.isSuccessful()) {
                                requestListener.onSuccess(response.body(), response.message());
                            } else {
                                UIUtils.showError(activity, response.errorBody());
                            }
                        }

                        @Override
                        public void onFailure(Call<Orders> call, Throwable t) {
                            requestListener.onFail(t.getLocalizedMessage());
                        }
                    });
        } else {
            UIUtils.showShortToast(activity, activity.getString(R.string.check_internet));
        }
    }*/

    public void getFilterOrders(Activity activity, String url, RequestListener<Orders> requestListener) {
        if (NetworkUtils.checkTheInternet()) {
            AppController.getInstance().getClientAPI().getFilterOrders(url)
                    .enqueue(new Callback<Orders>() {
                        @Override
                        public void onResponse(Call<Orders> call, Response<Orders> response) {
                            if (response.isSuccessful()) {
                                requestListener.onSuccess(response.body(), response.message());
                            } else {
                                UIUtils.showError(activity, response.errorBody());
                            }
                        }

                        @Override
                        public void onFailure(Call<Orders> call, Throwable t) {
                            requestListener.onFail(t.getLocalizedMessage());
                        }
                    });
        } else {
            UIUtils.showShortToast(activity, activity.getString(R.string.check_internet));
        }
    }

    public void getOrderDetails(Activity activity, long id, RequestListener<com.webapp.delavergy.models.OrderData> requestListener) {
        if (NetworkUtils.checkTheInternet()) {
            AppController.getInstance().getClientAPI().getOrderDetails(id)
                    .enqueue(new Callback<com.webapp.delavergy.models.OrderData>() {
                        @Override
                        public void onResponse(Call<com.webapp.delavergy.models.OrderData> call, Response<com.webapp.delavergy.models.OrderData> response) {
                            if (response.isSuccessful()) {
                                requestListener.onSuccess(response.body(), response.message());
                            } else {
                                UIUtils.showError(activity, response.errorBody());
                            }
                        }

                        @Override
                        public void onFailure(Call<com.webapp.delavergy.models.OrderData> call, Throwable t) {
                            requestListener.onFail(t.getLocalizedMessage());
                        }
                    });
        } else {
            UIUtils.showShortToast(activity, activity.getString(R.string.check_internet));
        }
    }

    public void pickOrder(Activity activity, long id, RequestListener<Result> requestListener) {
        if (NetworkUtils.checkTheInternet()) {
            AppController.getInstance().getClientAPI().pickOrder(id)
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

    public void deliveredOrder(Activity activity, long id, RequestListener<Result> requestListener) {
        if (NetworkUtils.checkTheInternet()) {
            AppController.getInstance().getClientAPI().deliveredOrder(id)
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

    public void getWallets(Activity activity, RequestListener<Wallets> requestListener) {
        if (NetworkUtils.checkTheInternet()) {
            AppController.getInstance().getClientAPI().getWallets()
                    .enqueue(new Callback<Wallets>() {
                        @Override
                        public void onResponse(Call<Wallets> call, Response<Wallets> response) {
                            if (response.isSuccessful()) {
                                requestListener.onSuccess(response.body(), response.message());
                            } else {
                                UIUtils.showError(activity, response.errorBody());
                            }
                        }

                        @Override
                        public void onFailure(Call<Wallets> call, Throwable t) {
                            requestListener.onFail(t.getLocalizedMessage());
                        }
                    });
        } else {
            UIUtils.showShortToast(activity, activity.getString(R.string.check_internet));
        }
    }
}
