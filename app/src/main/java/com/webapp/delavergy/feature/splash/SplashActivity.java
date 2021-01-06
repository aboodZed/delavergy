package com.webapp.delavergy.feature.splash;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.webapp.delavergy.R;
import com.webapp.delavergy.feature.login.LoginActivity;
import com.webapp.delavergy.feature.main.MainActivity;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.NavigateUtils;
import com.webapp.delavergy.utils.language.BaseActivity;

public class SplashActivity extends BaseActivity {

    private SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.setLayoutRes(R.layout.activity_splach);
        super.onCreate(savedInstanceState);
        //hide state bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        //presenter
        splashPresenter = new SplashPresenter(this, this::navigate);
    }

    @Override
    public void navigate(int page) {
        switch (page) {
            case 0:
                NavigateUtils.activityIntent(this, LoginActivity.class, false);
                break;
            case 1:
                NavigateUtils.activityIntent(this, MainActivity.class, false);
                break;
        }
    }
}