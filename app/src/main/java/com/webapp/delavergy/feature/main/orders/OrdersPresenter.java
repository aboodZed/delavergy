package com.webapp.delavergy.feature.main.orders;

import android.app.Activity;

import com.webapp.delavergy.R;
import com.webapp.delavergy.models.Orders;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.UIUtils;
import com.webapp.delavergy.utils.listener.DialogView;
import com.webapp.delavergy.utils.listener.RequestListener;

class OrdersPresenter {

    private Activity activity;
    private DialogView<Orders> dialogView;

    public OrdersPresenter(Activity activity, DialogView<Orders> dialogView) {
        this.activity = activity;
        this.dialogView = dialogView;
    }

   /* public void getOrders() {
        dialogView.showDialog(activity.getString(R.string.get_orders));
        AppController.getInstance().getGetAPIData().getOrderData()
                .getOrders(activity, new RequestListener<Orders>() {
                    @Override
                    public void onSuccess(Orders orders, String msg) {
                        dialogView.setData(orders);
                        dialogView.hideDialog();
                    }

                    @Override
                    public void onFail(String msg) {
                        UIUtils.showLongToast(activity, msg);
                        dialogView.hideDialog();
                    }
                });
    }*/

    public void getFilterOrders(String s) {
        dialogView.showDialog(activity.getString(R.string.get_orders));
        AppController.getInstance().getGetAPIData().getOrderData()
                .getFilterOrders(activity, s, new RequestListener<Orders>() {
                    @Override
                    public void onSuccess(Orders orders, String msg) {
                        dialogView.setData(orders);
                        dialogView.hideDialog();
                    }

                    @Override
                    public void onFail(String msg) {
                        UIUtils.showLongToast(activity, msg);
                        dialogView.hideDialog();
                    }
                });
    }
}
