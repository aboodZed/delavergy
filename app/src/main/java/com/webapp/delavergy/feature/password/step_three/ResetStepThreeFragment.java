package com.webapp.delavergy.feature.password.step_three;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.webapp.delavergy.R;
import com.webapp.delavergy.models.Result;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.dialog.BillDialogFragment;
import com.webapp.delavergy.utils.dialog.WaitDialogFragment;
import com.webapp.delavergy.utils.listener.DialogView;
import com.webapp.delavergy.utils.listener.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetStepThreeFragment extends Fragment implements DialogView<Result> {

    public static final int page = 103;

    @BindView(R.id.et_password) EditText etPassword;
    @BindView(R.id.et_conform_password) EditText etConformPassword;
    @BindView(R.id.btn_refresh) Button btnRefresh;

    private NavigationView navigationView;
    private ResetStepThreePresenter presenter;

    public ResetStepThreeFragment(NavigationView navigationView) {
        this.navigationView = navigationView;
    }

    public static ResetStepThreeFragment newInstance(NavigationView navigationView) {
        ResetStepThreeFragment fragment = new ResetStepThreeFragment(navigationView);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reset_step_three, container, false);
        ButterKnife.bind(this, view);
        presenter = new ResetStepThreePresenter(getActivity(), navigationView, this);
        AppController.getInstance().getAppLocal().removeMobile();
        return view;
    }

    @OnClick(R.id.btn_refresh)
    public void refresh() {
        presenter.validInput(etPassword, etConformPassword);
    }

    @Override
    public void setData(Result result) {

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