package com.webapp.delavergy.feature.password.step_one;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.webapp.delavergy.R;
import com.webapp.delavergy.feature.password.step_two.ResetStepTwoFragment;
import com.webapp.delavergy.utils.language.BaseActivity;
import com.webapp.delavergy.utils.listener.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetStepOneFragment extends Fragment {

    public static final int page = 101;

    @BindView(R.id.et_phone) EditText etPhone;
    @BindView(R.id.btn_send) Button btnSend;

    private NavigationView navigationView;

    public ResetStepOneFragment(NavigationView navigationView) {
        this.navigationView = navigationView;
    }

    public static ResetStepOneFragment newInstance(NavigationView navigationView) {
        return new ResetStepOneFragment(navigationView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reset_step_one, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_send)
    public void send() {
        navigationView.navigate(ResetStepTwoFragment.page);
    }
}