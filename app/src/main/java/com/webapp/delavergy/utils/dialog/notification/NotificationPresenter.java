package com.webapp.delavergy.utils.dialog.notification;

import android.app.Activity;

import com.webapp.delavergy.models.Notifications;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.ToolUtils;
import com.webapp.delavergy.utils.listener.DialogView;
import com.webapp.delavergy.utils.listener.RequestListener;

import java.util.ArrayList;

public class NotificationPresenter {

    private Activity activity;
    private DialogView<Notifications> dialogView;

    public NotificationPresenter(Activity activity, DialogView<Notifications> dialogView) {
        this.activity = activity;
        this.dialogView = dialogView;
        getData();
    }

    private void getData() {
        dialogView.showDialog("");
        AppController.getInstance().getGetAPIData().getNotificationData()
                .getNotification(activity, new RequestListener<Notifications>() {
                            @Override
                            public void onSuccess(Notifications notifications, String msg) {
                                dialogView.setData(notifications);
                                dialogView.hideDialog();

                            }

                            @Override
                            public void onFail(String msg) {
                                ToolUtils.showLongToast(activity, msg);
                                dialogView.hideDialog();
                            }
                        });
    }
}
