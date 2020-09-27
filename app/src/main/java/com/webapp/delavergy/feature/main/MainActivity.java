package com.webapp.delavergy.feature.main;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.webapp.delavergy.R;
import com.webapp.delavergy.feature.main.contact.ContactFragment;
import com.webapp.delavergy.feature.main.home.HomeFragment;
import com.webapp.delavergy.feature.main.orders.OrdersFragment;
import com.webapp.delavergy.feature.main.profile.ProfileFragment;
import com.webapp.delavergy.feature.main.wallet.WalletFragment;
import com.webapp.delavergy.feature.password.step_one.ResetStepOneFragment;
import com.webapp.delavergy.utils.AppContent;
import com.webapp.delavergy.utils.NavigateUtils;
import com.webapp.delavergy.utils.dialog.notification.NotificationFragment;
import com.webapp.delavergy.utils.language.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.sc_status) SwitchCompat scStatus;
    @BindView(R.id.imageView) ImageView imageView;
    @BindView(R.id.iv_notification) ImageView ivNotification;
    @BindView(R.id.fl_container) FrameLayout flContainer;

    private HomeFragment homeFragment;
    private WalletFragment walletFragment;
    private OrdersFragment ordersFragment;
    private ContactFragment contactFragment;
    private ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.setLayoutRes(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        initFragment();
    }

    private void initFragment() {
        if (getIntent().getExtras() != null) {
            navigate(getIntent().getExtras().getInt(AppContent.PAGE));
        } else {
            navigate(HomeFragment.page);
        }
    }

    @OnClick(R.id.iv_notification)
    public void notification() {
        NotificationFragment.newInstance().show(getSupportFragmentManager(), "");
    }

    @Override
    public void navigate(int page) {
        switch (page) {
            case HomeFragment.page:
                homeFragment = HomeFragment.newInstance();
                NavigateUtils.replaceFragment(getSupportFragmentManager(), homeFragment, R.id.fl_container);
                break;
            case WalletFragment.page:
                walletFragment = WalletFragment.newInstance();
                NavigateUtils.replaceFragment(getSupportFragmentManager(), walletFragment, R.id.fl_container);
                break;
            case OrdersFragment.page:
                ordersFragment = OrdersFragment.newInstance();
                NavigateUtils.replaceFragment(getSupportFragmentManager(), ordersFragment, R.id.fl_container);
                break;
            case ContactFragment.page:
                contactFragment = ContactFragment.newInstance();
                NavigateUtils.replaceFragment(getSupportFragmentManager(), contactFragment, R.id.fl_container);
                break;
            case ProfileFragment.page:
                profileFragment = ProfileFragment.newInstance();
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
}