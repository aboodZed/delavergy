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
import com.webapp.delavergy.feature.password.step_one.ResetStepOnePresenter;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.dialog.WaitDialogFragment;
import com.webapp.delavergy.utils.listener.DialogView;
import com.webapp.delavergy.utils.listener.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetStepTwoFragment extends Fragment implements DialogView  {

    public static final int page = 102;

    @BindView(R.id.spf_pin) SquarePinField spfPin;
    @BindView(R.id.tv_resend_text) TextView tvResendText;
    @BindView(R.id.btn_resend) Button btnResend;

    private NavigationView navigationView;
    private ResetStepTwoPresenter presenter;
    private ResetStepOnePresenter resetStepOnePresenter;

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
        presenter = new ResetStepTwoPresenter(getActivity(), navigationView, this);
        resetStepOnePresenter = new ResetStepOnePresenter(getActivity(), navigationView, this);
        action();
        return view;
    }

    public void action() {
        spfPin.setOnTextCompleteListener(s -> {
            presenter.verifyCode(s);
            return false;
        });
    }


    @OnClick(R.id.btn_resend)
    public void resend() {
        resetStepOnePresenter.forgetPassword(AppController.getInstance().getAppLocal().getMobile());
    }

    @Override
    public void setData(Object o) {
    }

    @Override
    public void showDialog(String s) {
        WaitDialogFragment.newInstance(s).show(getFragmentManager(), "");
    }

    @Override
    public void hideDialog() {
        WaitDialogFragment.newInstance("").dismiss();
    }
}