package com.webapp.delavergy.utils.dialog.privacy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.webapp.delavergy.R;
import com.webapp.delavergy.models.Privacy;
import com.webapp.delavergy.utils.dialog.notification.NotificationFragment;
import com.webapp.delavergy.utils.listener.DialogView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrivacyFragment extends DialogFragment implements DialogView<Privacy> {

    private static final String PRIVACY_KEY = "privacy";

    @BindView(R.id.iv_back) ImageView ivBack;
    @BindView(R.id.iv_notification) ImageView ivNotification;
    @BindView(R.id.tv_privacy) TextView tvPrivacy;
    @BindView(R.id.fl_load) FrameLayout flLoad;

    private PrivacyPresenter presenter;

    public static PrivacyFragment newInstance(String privacy) {
        PrivacyFragment privacyFragment = new PrivacyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(PRIVACY_KEY, privacy);
        privacyFragment.setArguments(bundle);
        return privacyFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.dialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_privacy, container, false);
        ButterKnife.bind(this, v);
        presenter = new PrivacyPresenter(getActivity(), this);
        tvPrivacy.setText(getArguments().getString(PRIVACY_KEY));
        hideDialog();
        return v;
    }

    @Override
    public void onResume() {
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getDialog().getWindow().setAttributes((WindowManager.LayoutParams) params);
        super.onResume();
        getDialog().setOnKeyListener((dialog, keyCode, event) -> {
            if ((keyCode == android.view.KeyEvent.KEYCODE_BACK)) {
                dismiss();
                return true;
            } else return false;
        });
    }

    @OnClick(R.id.iv_back)
    public void back() {
        dismiss();
    }

    @OnClick(R.id.iv_notification)
    public void OpeNotification() {
        NotificationFragment.newInstance().show(getChildFragmentManager(), "");
    }

    @Override
    public void setData(Privacy data) {
        tvPrivacy.setText(data.getPrivacy_content());
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