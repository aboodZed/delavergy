package com.webapp.delavergy.feature.login;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.webapp.delavergy.R;
import com.webapp.delavergy.feature.main.home.HomeFragment;
import com.webapp.delavergy.models.LoginResult;
import com.webapp.delavergy.models.User;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.ToolUtils;
import com.webapp.delavergy.utils.dialog.WaitDialogFragment;
import com.webapp.delavergy.utils.listener.DialogView;
import com.webapp.delavergy.utils.listener.NavigationView;
import com.webapp.delavergy.utils.listener.RequestListener;

import java.util.HashMap;
import java.util.Map;

class LoginPresenter {

    private Activity activity;
    private NavigationView navigationView;
    private DialogView<LoginResult> dialogView;

    public LoginPresenter(Activity activity, NavigationView navigationView, DialogView<LoginResult> dialogView) {
        this.activity = activity;
        this.navigationView = navigationView;
        this.dialogView = dialogView;
    }

    public void login(EditText etPhone, EditText etPassword) {
        Map<String, String> map = new HashMap<>();
        String phone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(phone)) {
            etPhone.setError(activity.getString(R.string.required_field));
            return;
        } else {
            map.put("mobile", phone);
        }


        if (TextUtils.isEmpty(password)) {
            etPassword.setError(activity.getString(R.string.required_field));
            return;
        }

        if (password.length() < 6) {
            etPassword.setError(activity.getString(R.string.six_char));
            return;
        } else {
            map.put("password", password);
        }

        map.put("remember_me", "True");

        dialogView.showDialog(activity.getString(R.string.login));

        AppController.getInstance().getGetAPIData().getAuthData()
                .login(activity, map, new RequestListener<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult, String msg) {
                        loginResult.getUser().setPassword(password);
                        AppController.getInstance().getAppLocal().setUser(loginResult);
                        AppController.getInstance().getAppLocal().setLogin(true);
                        navigationView.navigate(HomeFragment.page);
                        dialogView.hideDialog();

                    }

                    @Override
                    public void onFail(String msg) {
                        dialogView.hideDialog();
                        ToolUtils.showLongToast(activity, msg);
                    }
                });
    }
}
