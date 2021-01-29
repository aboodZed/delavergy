package com.webapp.delavergy.feature.main.home;

import android.app.Activity;

import com.webapp.delavergy.R;
import com.webapp.delavergy.models.Order;
import com.webapp.delavergy.models.OrderData;
import com.webapp.delavergy.models.Result;
import com.webapp.delavergy.utils.AppContent;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.NavigateUtils;
import com.webapp.delavergy.utils.UIUtils;
import com.webapp.delavergy.utils.listener.DialogView;
import com.webapp.delavergy.utils.listener.RequestListener;
import com.webapp.delavergy.utils.location.GPSTracking;

class HomePresenter {

    private Activity activity;
    private DialogView<Order> dialogView;

    public HomePresenter(Activity activity, DialogView<Order> dialogView) {
        this.activity = activity;
        this.dialogView = dialogView;
    }

    public void tracking() {
        GPSTracking.getInstance(activity).startMyGPSTracking();
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

    public void pickOrder(long order_id) {
        dialogView.showDialog(activity.getString(R.string.pick_up_order));
        AppController.getInstance().getGetAPIData()
                .getOrderData().pickOrder(activity, order_id,
                new RequestListener<Result>() {
                    @Override
                    public void onSuccess(Result result, String msg) {
                        dialogView.hideDialog();
                        NavigateUtils.openOrder(activity, order_id, true);
                        AppController.getInstance().getAppLocal().setUserStatus(AppContent.DRIVER_STATUS_BUSY);
                    }

                    @Override
                    public void onFail(String msg) {
                        UIUtils.showLongToast(activity, msg);
                        dialogView.hideDialog();
                    }
                });
    }
}
