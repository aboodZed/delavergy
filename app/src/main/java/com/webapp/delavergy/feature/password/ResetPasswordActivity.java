package com.webapp.delavergy.feature.password;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.webapp.delavergy.R;
import com.webapp.delavergy.feature.password.step_one.ResetStepOneFragment;
import com.webapp.delavergy.feature.password.step_three.ResetStepThreeFragment;
import com.webapp.delavergy.feature.password.step_two.ResetStepTwoFragment;
import com.webapp.delavergy.utils.AppContent;
import com.webapp.delavergy.utils.NavigateUtils;
import com.webapp.delavergy.utils.language.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ResetPasswordActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.fl_container)
    FrameLayout flContainer;

    private RestPasswordPresenter restPasswordPresenter;
    private ResetStepOneFragment resetStepOneFragment;
    private ResetStepTwoFragment resetStepTwoFragment;
    private ResetStepThreeFragment resetStepThreeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.setLayoutRes(R.layout.activity_reset_password);
        super.onCreate(savedInstanceState);
        //presenter
        restPasswordPresenter = new RestPasswordPresenter(this);
        //initFragment
        initFragment();
    }

    private void initFragment() {
        if (getIntent().getExtras() != null) {
            navigate(getIntent().getExtras().getInt(AppContent.PAGE));
        } else {
            navigate(ResetStepOneFragment.page);
        }
    }

    @OnClick(R.id.iv_back)
    public void back() {
        onBackPressed();
    }

    @Override
    public void navigate(int page) {
        switch (page) {
            case ResetStepOneFragment.page:
                resetStepOneFragment = ResetStepOneFragment.newInstance(this);
                NavigateUtils.replaceFragment(getSupportFragmentManager(), resetStepOneFragment, R.id.fl_container);
                break;
            case ResetStepTwoFragment.page:
                resetStepTwoFragment = ResetStepTwoFragment.newInstance(this);
                NavigateUtils.replaceFragment(getSupportFragmentManager(), resetStepTwoFragment, R.id.fl_container);
                break;
            case ResetStepThreeFragment.page:
                resetStepThreeFragment = ResetStepThreeFragment.newInstance();
                NavigateUtils.replaceFragment(getSupportFragmentManager(), resetStepThreeFragment, R.id.fl_container);
                break;
        }
    }
}