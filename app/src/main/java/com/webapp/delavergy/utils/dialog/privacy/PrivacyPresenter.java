package com.webapp.delavergy.utils.dialog.privacy;

import android.app.Activity;

import com.webapp.delavergy.utils.listener.DialogView;

class PrivacyPresenter {

    private Activity activity;
    private DialogView dialogView;

    public PrivacyPresenter(Activity activity, DialogView dialogView) {
        this.activity = activity;
        this.dialogView = dialogView;
        getData();
    }

    private void getData() {
    }
}
