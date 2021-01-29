package com.webapp.delavergy.feature.order;

import android.app.Activity;

import com.webapp.delavergy.R;
import com.webapp.delavergy.models.Order;
import com.webapp.delavergy.models.OrderData;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.UIUtils;
import com.webapp.delavergy.utils.listener.DialogView;
import com.webapp.delavergy.utils.listener.RequestListener;

public class OrderPresenter {

    private Activity activity;
    private DialogView<Order> dialogView;

    public OrderPresenter(Activity activity, DialogView<Order> dialogView) {
        this.activity = activity;
        this.dialogView = dialogView;
    }

    public void getOrderData(long id) {
        dialogView.showDialog(activity.getString(R.string.get_order));
        AppController.getInstance().getGetAPIData().getOrderData().getOrderDetails(activity, id
                , new RequestListener<OrderData>() {
                    @Override
                    public void onSuccess(OrderData orderData, String msg) {
                        dialogView.hideDialog();
                        dialogView.setData(orderData.getOrder());
                    }

                    @Override
                    public void onFail(String msg) {
                        dialogView.hideDialog();
                        UIUtils.showLongToast(activity, msg);
                    }
                });
    }
}
