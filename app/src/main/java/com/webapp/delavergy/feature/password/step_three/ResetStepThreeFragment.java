package com.webapp.delavergy.feature.password.step_three;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.webapp.delavergy.R;
import com.webapp.delavergy.utils.dialog.BillDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetStepThreeFragment extends Fragment {

    public static final int page = 103;

    @BindView(R.id.et_password) EditText etPassword;
    @BindView(R.id.et_conform_password) EditText etConformPassword;
    @BindView(R.id.btn_refresh) Button btnRefresh;



    public ResetStepThreeFragment() {
    }

    public static ResetStepThreeFragment newInstance() {
        ResetStepThreeFragment fragment = new ResetStepThreeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reset_step_three, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_refresh)
    public void refresh(){
    }
}