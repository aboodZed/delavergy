package com.webapp.delavergy.feature.main;

import android.app.Activity;

import androidx.appcompat.widget.SwitchCompat;

import com.webapp.delavergy.models.LoginResult;
import com.webapp.delavergy.models.Result;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.UIUtils;
import com.webapp.delavergy.utils.listener.DialogView;
import com.webapp.delavergy.utils.listener.RequestListener;

class MainPresenter {

    private Activity activity;
    private DialogView<Result> dialogView;

    public MainPresenter(Activity activity, DialogView<Result> dialogView) {
        this.activity = activity;
        this.dialogView = dialogView;
    }

    public void changeStatus(int i) {
        AppController.getInstance().getGetAPIData().getAuthData()
                .changeStatus(activity, i, new RequestListener<Result>() {
                    @Override
                    public void onSuccess(Result result, String msg) {
                        dialogView.setData(result);
                        LoginResult loginResult = AppController.getInstance()
                                .getAppLocal().getUser();
                        loginResult.getUser().setIs_online(i + "");
                        AppController.getInstance().getAppLocal().setUser(loginResult);
                    }

                    @Override
                    public void onFail(String msg) {
                        UIUtils.showLongToast(activity, msg);
                    }
                });
    }

    public void updateSwitch(SwitchCompat scStatus) {
        scStatus.setChecked(AppController.getInstance().getAppLocal().getUser().getUser().getIs_online());
    }
}
