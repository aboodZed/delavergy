package com.webapp.delavergy.feature.main.orders;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.webapp.delavergy.R;

import butterknife.ButterKnife;

public class OrdersFragment extends Fragment {

    public static final int page = 203;

    private OrdersFragment() {}

    public static OrdersFragment newInstance() {
        OrdersFragment fragment = new OrdersFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        //ButterKnife
        ButterKnife.bind(this, view);
        //presenter
        return view;
    }
}