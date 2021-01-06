package com.webapp.delavergy.feature.splash;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import com.webapp.delavergy.feature.login.LoginActivity;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.NavigateUtils;
import com.webapp.delavergy.utils.listener.NavigationView;

class SplashPresenter {

    private Activity activity;
    private NavigationView navigationView;

    public SplashPresenter(Activity activity, NavigationView navigationView) {
        this.activity = activity;
        this.navigationView = navigationView;
        checkLogin();
    }

    private void checkLogin() {
        new Handler().postDelayed(() -> {
                    if (AppController.getInstance().getAppLocal().isLogin()) {
                        if (!AppController.getInstance().getAppLocal().getUser().getAccess_token().equals("")) {
                            Log.e(getClass().getName() + " : user_token",
                                    AppController.getInstance().getAppLocal().getUser().getAccess_token());
                        }
                        navigationView.navigate(1);
                    } else {
                        navigationView.navigate(0);
                    }
                }
                , 3000);
    }
}
