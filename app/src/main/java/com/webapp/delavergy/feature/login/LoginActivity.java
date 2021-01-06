package com.webapp.delavergy.feature.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.webapp.delavergy.R;
import com.webapp.delavergy.feature.main.MainActivity;
import com.webapp.delavergy.feature.main.home.HomeFragment;
import com.webapp.delavergy.feature.password.ResetPasswordActivity;
import com.webapp.delavergy.feature.password.step_one.ResetStepOneFragment;
import com.webapp.delavergy.models.LoginResult;
import com.webapp.delavergy.utils.NavigateUtils;
import com.webapp.delavergy.utils.dialog.WaitDialogFragment;
import com.webapp.delavergy.utils.language.BaseActivity;
import com.webapp.delavergy.utils.listener.DialogView;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements DialogView<LoginResult> {

    @BindView(R.id.et_phone) EditText etPhone;
    @BindView(R.id.et_password) EditText etPassword;
    @BindView(R.id.btn_login) Button btnLogin;
    @BindView(R.id.btn_password_recovery) Button btnPasswordRecovery;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.setLayoutRes(R.layout.activity_login);
        super.onCreate(savedInstanceState);
        //hide state bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        //presenter
        loginPresenter = new LoginPresenter(this, this, this);
    }

    @OnClick(R.id.btn_login)
    public void login() {
        loginPresenter.login(etPhone, etPassword);
        //navigate(HomeFragment.page);
    }

    @OnClick(R.id.btn_password_recovery)
    public void recovery() {
        navigate(ResetStepOneFragment.page);
    }

    @Override
    public void navigate(int page) {
        switch (page) {
            case HomeFragment.page:
                NavigateUtils.activityIntent(this, MainActivity.class, false);
                break;
            case ResetStepOneFragment.page:
                NavigateUtils.activityIntent(this, ResetPasswordActivity.class, true);
                break;
        }
    }

    @Override
    public void setData(LoginResult loginResult) {

    }

    @Override
    public void showDialog(String s) {
        WaitDialogFragment.newInstance(s).show(getSupportFragmentManager(), "");
    }

    @Override
    public void hideDialog() {
        WaitDialogFragment.newInstance("").dismiss();
    }
}