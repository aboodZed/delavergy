package com.webapp.delavergy.feature.main.contact;

import android.app.Activity;

import com.webapp.delavergy.R;
import com.webapp.delavergy.models.Settings;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.ToolUtils;
import com.webapp.delavergy.utils.listener.DialogView;
import com.webapp.delavergy.utils.listener.RequestListener;

class ContactPresenter {

    private Activity activity;
    private DialogView<Settings> dialogView;

    public ContactPresenter(Activity activity, DialogView<Settings> dialogView) {
        this.activity = activity;
        this.dialogView = dialogView;
    }

    public void getSetting() {
        dialogView.showDialog(activity.getString(R.string.getSettings));
        AppController.getInstance().getGetAPIData().getSettingData()
                .getSettings(activity, new RequestListener<Settings>() {
                    @Override
                    public void onSuccess(Settings settings, String msg) {
                        dialogView.setData(settings);
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
