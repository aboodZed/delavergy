package com.webapp.delavergy.feature.main.home;

import android.app.Activity;

import com.webapp.delavergy.models.Order;
import com.webapp.delavergy.utils.listener.DialogView;
import com.webapp.delavergy.utils.location.GPSTracking;

class HomePresenter {

    private Activity activity;
    private DialogView<Order> dialogView;

    public HomePresenter(Activity activity, DialogView<Order> dialogView) {
        this.activity = activity;
        this.dialogView = dialogView;
    }

    public void tracking(){
        GPSTracking.getInstance(activity).startMyGPSTracking();
    }

    public void getOrderData() {
        dialogView.setData(new Order());
    }
}
