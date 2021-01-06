package com.webapp.delavergy.feature.main.contact;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.webapp.delavergy.R;
import com.webapp.delavergy.models.Settings;
import com.webapp.delavergy.utils.dialog.SendMessageDialog;
import com.webapp.delavergy.utils.dialog.WaitDialogFragment;
import com.webapp.delavergy.utils.dialog.privacy.PrivacyFragment;
import com.webapp.delavergy.utils.listener.DialogView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactFragment extends Fragment implements DialogView<Settings> {

    public static final int page = 204;

    @BindView(R.id.tv_phone) TextView tvPhone;
    @BindView(R.id.tv_email) TextView tvEmail;
    @BindView(R.id.iv_facebook) ImageView ivFacebook;
    @BindView(R.id.iv_twitter) ImageView ivTwitter;
    @BindView(R.id.iv_instagram) ImageView ivInstagram;
    @BindView(R.id.iv_linkin) ImageView ivLinkin;
    @BindView(R.id.tv_terms) TextView tvTerms;
    @BindView(R.id.btn_send) Button btnSend;

    private ContactPresenter presenter;

    public static ContactFragment newInstance() {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        ButterKnife.bind(this, view);
        //presenter
        presenter = new ContactPresenter(getActivity(), this);
        presenter.getSetting();
        return view;
    }

    @OnClick(R.id.tv_terms)
    public void opeTerms() {
        PrivacyFragment.newInstance().show(getChildFragmentManager(), "");
    }

    @OnClick(R.id.btn_send)
    public void send() {
        SendMessageDialog.newInstance().show(getChildFragmentManager(), "");
    }

    @Override
    public void setData(Settings settings) {

    }

    @Override
    public void showDialog(String s) {
        WaitDialogFragment.newInstance(s).show(getChildFragmentManager(), "");
    }

    @Override
    public void hideDialog() {
        WaitDialogFragment.newInstance("").dismiss();
    }
}