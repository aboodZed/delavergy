package com.webapp.delavergy.feature.main;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.webapp.delavergy.R;
import com.webapp.delavergy.feature.main.contact.ContactFragment;
import com.webapp.delavergy.feature.main.home.HomeFragment;
import com.webapp.delavergy.feature.main.orders.OrdersFragment;
import com.webapp.delavergy.feature.main.profile.ProfileFragment;
import com.webapp.delavergy.feature.main.wallet.WalletFragment;
import com.webapp.delavergy.models.Result;
import com.webapp.delavergy.utils.AppContent;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.NavigateUtils;
import com.webapp.delavergy.utils.UIUtils;
import com.webapp.delavergy.utils.dialog.notification.NotificationFragment;
import com.webapp.delavergy.utils.language.BaseActivity;
import com.webapp.delavergy.utils.listener.DialogView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity implements DialogView<Result> {

    @BindView(R.id.sc_status) SwitchCompat scStatus;
    @BindView(R.id.imageView) ImageView imageView;
    @BindView(R.id.iv_notification) ImageView ivNotification;
    @BindView(R.id.fl_container) FrameLayout flContainer;
    @BindView(R.id.tv_home) TextView tvHome;
    @BindView(R.id.tv_wallet) TextView tvWallet;
    @BindView(R.id.tv_orders) TextView tvOrders;
    @BindView(R.id.tv_contact) TextView tvContact;
    @BindView(R.id.ll_profile) LinearLayout llProfile;
    @BindView(R.id.civ_user) CircleImageView civUser;
    @BindView(R.id.tv_profile) TextView tvProfile;

    private HomeFragment homeFragment;
    private WalletFragment walletFragment;
    private OrdersFragment ordersFragment;
    private ContactFragment contactFragment;
    private ProfileFragment profileFragment;

    private MainPresenter presenter;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.setLayoutRes(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        presenter = new MainPresenter(this, this);
        presenter.updateSwitch(scStatus);
        initFragment();
    }

    private void initFragment() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String message = bundle.getString(AppContent.FIREBASE_MESSAGE);
            getMessageNotification(message);

            if (bundle.getInt(AppContent.PAGE) != 0) {
                navigate(bundle.getInt(AppContent.PAGE));
            } else {
                navigate(HomeFragment.page);
            }
        } else {
            navigate(HomeFragment.page);
        }
    }

    private void getMessageNotification(String message) {
        try {
            JSONObject body = new JSONObject(message).getJSONObject(AppContent.FIREBASE_DATA_BODY);

            id = body.getInt(AppContent.ORDER_Id);
            String type = body.getString(AppContent.NOTIFICATION_TYPE);
            String msg = body.getString(AppContent.NOTIFICATION_MESSAGE);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("error", "" + e.getMessage());
        }
    }

    @OnClick(R.id.iv_notification)
    public void notification() {
        NotificationFragment.newInstance().show(getSupportFragmentManager(), "");
    }

    @OnClick(R.id.sc_status)
    public void online() {
        int i = 0;
        if (scStatus.isChecked()) {
            i = 1;
        }
        presenter.changeStatus(i);
    }

    @OnClick(R.id.tv_home)
    public void home() {
        navigate(HomeFragment.page);
    }

    @OnClick(R.id.tv_wallet)
    public void wallet() {
        navigate(WalletFragment.page);
    }

    @OnClick(R.id.tv_orders)
    public void orders() {
        navigate(OrdersFragment.page);
    }

    @OnClick(R.id.tv_contact)
    public void schedule() {
        navigate(ContactFragment.page);
    }

    @OnClick(R.id.ll_profile)
    public void profile() {
        navigate(ProfileFragment.page);
    }

    @Override
    public void navigate(int page) {
        tvHome.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_un_home, 0, 0);
        tvHome.setTextColor(getColor(R.color.darkGray));
        tvWallet.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_un_wallet, 0, 0);
        tvWallet.setTextColor(getColor(R.color.darkGray));
        tvOrders.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_un_orders, 0, 0);
        tvOrders.setTextColor(getColor(R.color.darkGray));
        tvContact.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_un_contact, 0, 0);
        tvContact.setTextColor(getColor(R.color.darkGray));
        tvProfile.setTextColor(getColor(R.color.darkGray));
        switch (page) {
            case HomeFragment.page:
                homeFragment = HomeFragment.newInstance(id);
                id = 0;
                tvHome.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_home, 0, 0);
                tvHome.setTextColor(getColor(R.color.colorAccent));
                NavigateUtils.replaceFragment(getSupportFragmentManager(), homeFragment, R.id.fl_container);
                break;
            case WalletFragment.page:
                walletFragment = WalletFragment.newInstance();
                tvWallet.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_wallet, 0, 0);
                tvWallet.setTextColor(getColor(R.color.colorAccent));
                NavigateUtils.replaceFragment(getSupportFragmentManager(), walletFragment, R.id.fl_container);
                break;
            case OrdersFragment.page:
                ordersFragment = OrdersFragment.newInstance();
                tvOrders.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_orders, 0, 0);
                tvOrders.setTextColor(getColor(R.color.colorAccent));
                NavigateUtils.replaceFragment(getSupportFragmentManager(), ordersFragment, R.id.fl_container);
                break;
            case ContactFragment.page:
                contactFragment = ContactFragment.newInstance();
                tvContact.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_contact, 0, 0);
                tvContact.setTextColor(getColor(R.color.colorAccent));
                NavigateUtils.replaceFragment(getSupportFragmentManager(), contactFragment, R.id.fl_container);
                break;
            case ProfileFragment.page:
                profileFragment = ProfileFragment.newInstance();
                tvProfile.setTextColor(getColor(R.color.colorAccent));
                NavigateUtils.replaceFragment(getSupportFragmentManager(), profileFragment, R.id.fl_container);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fl_container);
        if (fragment == homeFragment) {
            super.onBackPressed();
        } else {
            navigate(HomeFragment.page);
        }
    }

    @Override
    public void setData(Result result) {
        UIUtils.showLongToast(this, result.getMessage());
        if (!result.isSuccess()) {
            if (scStatus.isChecked()) {
                scStatus.setChecked(false);
            } else {
                scStatus.setChecked(true);
            }
        } else {
            if (!AppController.getInstance().getAppLocal().getUserStatus().equals(AppContent.DRIVER_STATUS_BUSY))
                if (scStatus.isChecked()) {
                    AppController.getInstance().getAppLocal().setUserStatus(AppContent.DRIVER_STATUS_ONLINE);
                } else {
                    AppController.getInstance().getAppLocal().setUserStatus(AppContent.DRIVER_STATUS_OFFLINE);
                }
        }
    }

    @Override
    public void showDialog(String s) {

    }

    @Override
    public void hideDialog() {

    }
}