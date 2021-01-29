package com.webapp.delavergy.feature.main.wallet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.webapp.delavergy.R;
import com.webapp.delavergy.models.Wallets;
import com.webapp.delavergy.utils.adapters.WalletAdapter;
import com.webapp.delavergy.utils.listener.DialogView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WalletFragment extends Fragment implements DialogView<Wallets> {

    public static final int page = 202;

    @BindView(R.id.tv_total_balance) TextView tvTotalBalance;
    @BindView(R.id.tv_your_percentage_of_orders) TextView tvYourPercentageOfOrders;
    @BindView(R.id.tv_total_delivery_value) TextView tvTotalDeliveryValue;
    @BindView(R.id.tv_captain_dues) TextView tvCaptainDues;
    @BindView(R.id.tv_company_dues) TextView tvCompanyDues;
    @BindView(R.id.tv_orders_num) TextView tvOrdersNum;
    @BindView(R.id.rv_wallet) RecyclerView rvWallet;
    @BindView(R.id.fl_load) FrameLayout flLoad;

    private WalletAdapter walletAdapter;
    private WalletPresenter presenter;

    public static WalletFragment newInstance() {
        WalletFragment fragment = new WalletFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);
        ButterKnife.bind(this, view);
        //init
        presenter = new WalletPresenter(getActivity(), this);
        presenter.getWallets();
        return view;
    }

    private void initRecycle(Wallets wallets) {
        walletAdapter = new WalletAdapter(getActivity());
        rvWallet.setLayoutManager(new LinearLayoutManager(getContext()));
        rvWallet.setItemAnimator(new DefaultItemAnimator());
        rvWallet.setAdapter(walletAdapter);
        walletAdapter.setWallets(wallets.getWallet_details());
    }

    @Override
    public void setData(Wallets wallets) {
        initRecycle(wallets);
        tvTotalBalance.setText(wallets.getWallet() + "");
        tvYourPercentageOfOrders.setText(wallets.getApp_commission() + "");
        tvTotalDeliveryValue.setText(wallets.getTotal_delivery_cost() + "");
        tvCaptainDues.setText(wallets.getTotal_driver_revenue() + "");
        tvCompanyDues.setText(wallets.getApp_revenue() + "");
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