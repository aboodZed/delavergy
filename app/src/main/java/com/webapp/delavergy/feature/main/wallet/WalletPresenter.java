package com.webapp.delavergy.feature.main.wallet;

import android.app.Activity;

import com.webapp.delavergy.models.Wallets;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.UIUtils;
import com.webapp.delavergy.utils.listener.DialogView;
import com.webapp.delavergy.utils.listener.RequestListener;

class WalletPresenter {

    private Activity activity;
    private DialogView<Wallets> dialogView;

    public WalletPresenter(Activity activity, DialogView<Wallets> dialogView) {
        this.activity = activity;
        this.dialogView = dialogView;
    }

    public void getWallets() {
        dialogView.showDialog("");
        AppController.getInstance().getGetAPIData().getOrderData()
                .getWallets(activity, new RequestListener<Wallets>() {
                    @Override
                    public void onSuccess(Wallets wallets, String msg) {
                        dialogView.setData(wallets);
                        dialogView.hideDialog();
                    }

                    @Override
                    public void onFail(String msg) {
                        UIUtils.showLongToast(activity, msg);
                        dialogView.hideDialog();
                    }
                });
    }
}
