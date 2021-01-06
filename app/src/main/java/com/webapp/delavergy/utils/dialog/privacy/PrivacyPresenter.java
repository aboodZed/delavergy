package com.webapp.delavergy.utils.dialog.privacy;

import android.app.Activity;

import com.webapp.delavergy.R;
import com.webapp.delavergy.models.Privacy;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.ToolUtils;
import com.webapp.delavergy.utils.listener.DialogView;
import com.webapp.delavergy.utils.listener.RequestListener;

class PrivacyPresenter {

    private Activity activity;
    private DialogView<Privacy> dialogView;

    public PrivacyPresenter(Activity activity, DialogView<Privacy> dialogView) {
        this.activity = activity;
        this.dialogView = dialogView;
        getData();
    }

    private void getData() {
        dialogView.showDialog(activity.getString(R.string.get_privacy));
        AppController.getInstance().getGetAPIData().getSettingData()
                .getPrivacy(activity, new RequestListener<Privacy>() {
                    @Override
                    public void onSuccess(Privacy s, String msg) {
                        dialogView.setData(s);
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
