package com.webapp.delavergy.feature.password.step_one;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.webapp.delavergy.R;
import com.webapp.delavergy.models.Code;
import com.webapp.delavergy.utils.ToolUtils;
import com.webapp.delavergy.utils.dialog.WaitDialogFragment;
import com.webapp.delavergy.utils.listener.DialogView;
import com.webapp.delavergy.utils.listener.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetStepOneFragment extends Fragment implements DialogView {

    public static final int page = 101;

    @BindView(R.id.et_phone) EditText etPhone;
    @BindView(R.id.btn_send) Button btnSend;

    private NavigationView navigationView;
    private ResetStepOnePresenter presenter;

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
        presenter = new ResetStepOnePresenter(getActivity(), navigationView, this);
        return view;
    }

    @OnClick(R.id.btn_send)
    public void send() {
        presenter.validInput(etPhone);
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