package com.webapp.delavergy.utils.dialog.notification;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.webapp.delavergy.R;
import com.webapp.delavergy.models.Notifications;
import com.webapp.delavergy.utils.adapters.NotificationAdapter;
import com.webapp.delavergy.utils.listener.DialogView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotificationFragment extends DialogFragment implements DialogView<Notifications> {

    @BindView(R.id.iv_back) ImageView ivBack;
    @BindView(R.id.rv_list) RecyclerView rvList;
    @BindView(R.id.fl_load) FrameLayout flLoad;

    private NotificationAdapter notificationAdapter;
    private NotificationPresenter notificationPresenter;

    public NotificationFragment() {
    }

    public static NotificationFragment newInstance() {
        NotificationFragment fragment = new NotificationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.dialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        ButterKnife.bind(this, view);
        //init
        initRecycle();
        notificationPresenter = new NotificationPresenter(getActivity(), this);
        return view;
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

    private void initRecycle() {
        notificationAdapter = new NotificationAdapter(getActivity());
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvList.setItemAnimator(new DefaultItemAnimator());
        rvList.setAdapter(notificationAdapter);
    }

    @OnClick(R.id.iv_back)
    public void back() {
        dismiss();
    }

    @Override
    public void setData(Notifications data) {
        notificationAdapter.setNotifications(data.getNotifications());
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