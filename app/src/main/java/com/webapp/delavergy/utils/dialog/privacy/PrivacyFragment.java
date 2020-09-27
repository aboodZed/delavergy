package com.webapp.delavergy.utils.dialog.privacy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.webapp.delavergy.R;
import com.webapp.delavergy.utils.listener.DialogView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PrivacyFragment extends DialogFragment implements DialogView<ArrayList> {

    @BindView(R.id.tv_privacy) TextView tvPrivacy;
    @BindView(R.id.fl_load) FrameLayout flLoad;
    private PrivacyPresenter presenter;

    public static PrivacyFragment newInstance() {
        return new PrivacyFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_privacy, container, false);
        ButterKnife.bind(this, v);
        presenter = new PrivacyPresenter(getActivity(), this);
        return v;
    }

    @Override
    public void onResume() {
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((WindowManager.LayoutParams) params);
        super.onResume();
        getDialog().setOnKeyListener((dialog, keyCode, event) -> {
            if ((keyCode == android.view.KeyEvent.KEYCODE_BACK)) {
                dismiss();
                return true;
            } else return false;
        });
    }

    @Override
    public void setData(ArrayList data) {

    }

    @Override
    public void showDialog(String s) {
        flLoad.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideDialog() {
        flLoad.setVisibility(View.GONE);
    }
}