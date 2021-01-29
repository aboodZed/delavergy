package com.webapp.delavergy.feature.main.profile;

import android.app.Activity;

import com.webapp.delavergy.R;
import com.webapp.delavergy.models.User;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.UIUtils;
import com.webapp.delavergy.utils.listener.DialogView;
import com.webapp.delavergy.utils.listener.RequestListener;

class ProfilePresenter {

    private Activity activity;
    private DialogView<User> dialogView;

    public ProfilePresenter(Activity activity, DialogView<User> dialogView) {
        this.activity = activity;
        this.dialogView = dialogView;

        getUserData();
    }

    private void getUserData() {
        dialogView.showDialog(activity.getString(R.string.get_user_data));
        AppController.getInstance().getGetAPIData().getAuthData()
                .getProfile(activity, new RequestListener<User>() {
                    @Override
                    public void onSuccess(User user, String msg) {
                        dialogView.hideDialog();
                        dialogView.setData(user);
                    }

                    @Override
                    public void onFail(String msg) {
                        UIUtils.showLongToast(activity, msg);
                        dialogView.hideDialog();
                    }
                });
    }
}
