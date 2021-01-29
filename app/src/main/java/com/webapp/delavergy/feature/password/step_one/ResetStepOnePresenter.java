package com.webapp.delavergy.feature.password.step_one;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.EditText;

import com.webapp.delavergy.R;
import com.webapp.delavergy.feature.password.step_two.ResetStepTwoFragment;
import com.webapp.delavergy.models.Code;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.UIUtils;
import com.webapp.delavergy.utils.listener.DialogView;
import com.webapp.delavergy.utils.listener.NavigationView;
import com.webapp.delavergy.utils.listener.RequestListener;

public class ResetStepOnePresenter {

    private Activity activity;
    private NavigationView navigationView;
    private DialogView dialogView;

    public ResetStepOnePresenter(Activity activity, NavigationView navigationView, DialogView dialogView) {
        this.activity = activity;
        this.navigationView = navigationView;
        this.dialogView = dialogView;
    }

    public void validInput(EditText etPhone) {
        String phone = etPhone.getText().toString().trim();

        if (TextUtils.isEmpty(phone)) {
            etPhone.setError(activity.getString(R.string.required_field));
            return;
        }

        forgetPassword(phone);
    }

    public void forgetPassword(String phone) {
        dialogView.showDialog(activity.getString(R.string.reset_password_request));
        AppController.getInstance().getGetAPIData().getPasswordData()
                .forgetPassword(activity, phone, new RequestListener<Code>() {
                    @Override
                    public void onSuccess(Code code, String msg) {
                        dialogView.hideDialog();
                        UIUtils.showLongToast(activity, code.getCode() + "");
                        AppController.getInstance().getAppLocal().setMobile(phone);
                        navigationView.navigate(ResetStepTwoFragment.page);
                    }

                    @Override
                    public void onFail(String msg) {
                        dialogView.hideDialog();
                        UIUtils.showLongToast(activity, msg);
                    }
                });
    }
}
