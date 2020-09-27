package com.webapp.delavergy.feature.password.step_two;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.poovam.pinedittextfield.SquarePinField;
import com.webapp.delavergy.R;
import com.webapp.delavergy.feature.password.step_three.ResetStepThreeFragment;
import com.webapp.delavergy.utils.listener.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetStepTwoFragment extends Fragment {

    public static final int page = 102;

    @BindView(R.id.spf_pin) SquarePinField spfPin;
    @BindView(R.id.tv_resend_text) TextView tvResendText;
    @BindView(R.id.btn_resend) Button btnResend;

    private NavigationView navigationView;

    public ResetStepTwoFragment(NavigationView navigationView) {
        this.navigationView = navigationView;
    }

    public static ResetStepTwoFragment newInstance(NavigationView navigationView) {
       return new ResetStepTwoFragment(navigationView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reset_step_two, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_resend)
    public void resend(){
        navigationView.navigate(ResetStepThreeFragment.page);
    }
}