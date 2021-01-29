package com.webapp.delavergy.feature.password.step_three;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.EditText;

import com.webapp.delavergy.R;
import com.webapp.delavergy.models.Result;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.UIUtils;
import com.webapp.delavergy.utils.listener.DialogView;
import com.webapp.delavergy.utils.listener.NavigationView;
import com.webapp.delavergy.utils.listener.RequestListener;

public class ResetStepThreePresenter {

    private Activity activity;
    private NavigationView navigationView;
    private DialogView<Result> dialogView;

    public ResetStepThreePresenter(Activity activity, NavigationView navigationView, DialogView<Result> dialogView) {
        this.activity = activity;
        this.navigationView = navigationView;
        this.dialogView = dialogView;
    }

    public void validInput(EditText etPassword, EditText etConformPassword) {
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConformPassword.getText().toString().trim();

        if (TextUtils.isEmpty(password)) {
            etPassword.setError(activity.getString(R.string.required_field));
            return;
        }

        if (password.length() < 6) {
            etPassword.setError(activity.getString(R.string.six_char));
            return;
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            etConformPassword.setError(activity.getString(R.string.required_field));
            return;
        }

        if (confirmPassword.length() < 6) {
            etConformPassword.setError(activity.getString(R.string.six_char));
            return;
        }

        if (!password.equals(confirmPassword)) {
            etConformPassword.setError(activity.getString(R.string.not_match));
            return;
        }

        resetPassword(password);
    }

    public void resetPassword(String password) {
        dialogView.showDialog(activity.getString(R.string.reset_password_string));
        AppController.getInstance().getGetAPIData().getPasswordData().resetPassword(activity,
                password, new RequestListener<Result>() {
                    @Override
                    public void onSuccess(Result result, String msg) {
                        dialogView.hideDialog();
                        UIUtils.showLongToast(activity, result.getMessage());
                        navigationView.navigate(0);
                    }

                    @Override
                    public void onFail(String msg) {
                        dialogView.hideDialog();
                        UIUtils.showLongToast(activity, msg);
                    }
                });
    }
}
