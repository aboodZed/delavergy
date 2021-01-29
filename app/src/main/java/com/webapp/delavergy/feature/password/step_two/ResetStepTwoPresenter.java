package com.webapp.delavergy.feature.password.step_two;

import android.app.Activity;
import android.text.TextUtils;

import com.webapp.delavergy.R;
import com.webapp.delavergy.feature.password.step_three.ResetStepThreeFragment;
import com.webapp.delavergy.models.LoginResult;
import com.webapp.delavergy.models.Verify;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.UIUtils;
import com.webapp.delavergy.utils.listener.DialogView;
import com.webapp.delavergy.utils.listener.NavigationView;
import com.webapp.delavergy.utils.listener.RequestListener;

public class ResetStepTwoPresenter {

    private Activity activity;
    private NavigationView navigationView;
    private DialogView dialogView;

    public ResetStepTwoPresenter(Activity activity, NavigationView navigationView, DialogView dialogView) {
        this.activity = activity;
        this.navigationView = navigationView;
        this.dialogView = dialogView;
    }

    public void verifyCode(String code) {

        if (TextUtils.isEmpty(code)) {
            UIUtils.showShortToast(activity, activity.getString(R.string.required_field));
            return;
        }

        dialogView.showDialog(activity.getString(R.string.verify_code));

        AppController.getInstance().getGetAPIData().getPasswordData().verifyCode(
                activity, AppController.getInstance().getAppLocal().getMobile(),
                code, new RequestListener<Verify>() {
                    @Override
                    public void onSuccess(Verify verify, String msg) {
                        dialogView.hideDialog();
                        LoginResult loginResult = AppController.getInstance().getAppLocal().getUser();
                        loginResult.setAccess_token(verify.getAccess_token());
                        AppController.getInstance().getAppLocal().setUser(loginResult);
                        navigationView.navigate(ResetStepThreeFragment.page);
                    }

                    @Override
                    public void onFail(String msg) {
                        dialogView.hideDialog();
                        UIUtils.showLongToast(activity, msg);
                    }
                }
        );
    }
}
