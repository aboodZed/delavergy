package com.webapp.delavergy.feature.main.orders;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.webapp.delavergy.R;
import com.webapp.delavergy.models.Orders;
import com.webapp.delavergy.utils.AppContent;
import com.webapp.delavergy.utils.UIUtils;
import com.webapp.delavergy.utils.adapters.DateSpinnerAdapter;
import com.webapp.delavergy.utils.adapters.OrdersAdapter;
import com.webapp.delavergy.utils.adapters.SpinnerAdapter;
import com.webapp.delavergy.utils.dialog.WaitDialogFragment;
import com.webapp.delavergy.utils.listener.DialogView;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrdersFragment extends Fragment implements DialogView<Orders> {

    public static final int page = 203;

    @BindView(R.id.sp_date) Spinner spDate;
    @BindView(R.id.sp_order_type) Spinner spOrderType;
    @BindView(R.id.btn_filter) Button btnFilter;
    @BindView(R.id.ll_processing_orders) LinearLayout llProcessingOrders;
    @BindView(R.id.tv_processing_orders) TextView tvProcessingOrders;
    @BindView(R.id.v_p_order_line) View vPOrderLine;
    @BindView(R.id.ll_finished_orders) LinearLayout llFinishedOrders;
    @BindView(R.id.tv_finished_orders) TextView tvFinishedOrders;
    @BindView(R.id.v_f_order_line) View vFOrderLine;
    @BindView(R.id.rv_orders) RecyclerView rvOrders;

    private SpinnerAdapter orderTypes;
    private DateSpinnerAdapter orderDates;
    private OrdersPresenter presenter;
    private OrdersAdapter ordersAdapter;
    private Orders orders;

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
        initTypeSpinner();
        presenter = new OrdersPresenter(getActivity(), this);
        filterOrders();
        //init
        return view;
    }

    public void initTypeSpinner() {
        //types
        ArrayList<String> types = new ArrayList<>();
        //types.add(getString(R.string.all_orders));
        types.add(getString(R.string.administrative));
        types.add(getString(R.string.liaison_officer));
        orderTypes = new SpinnerAdapter(getContext(), types);
        spOrderType.setAdapter(orderTypes);
    }

    public void initSpinner(Orders orders) {
        //dates
        ArrayList<Long> dates = new ArrayList<>();
        for (int i = 0; i < orders.getPrevious_orders().size(); i++) {
            if (!dates.contains(orders.getPrevious_orders().get(i).getCreated_timestamp())) {
                dates.add(orders.getPrevious_orders().get(i).getCreated_timestamp());
            }
        }

        for (int i = 0; i < orders.getCurrent_orders().size(); i++) {
            if (!dates.contains(orders.getCurrent_orders().get(i).getCreated_timestamp())) {
                dates.add(orders.getCurrent_orders().get(i).getCreated_timestamp());
            }
        }
        //dates.add((long) 1611863511);
        orderDates = new DateSpinnerAdapter(getContext(), dates);
        spDate.setAdapter(orderDates);
    }

    private void initRecycle() {
        ordersAdapter = new OrdersAdapter(getActivity());
        rvOrders.setLayoutManager(new LinearLayoutManager(getContext()));
        rvOrders.setItemAnimator(new DefaultItemAnimator());
        rvOrders.setAdapter(ordersAdapter);
        processingOrders();
    }

    @OnClick(R.id.ll_processing_orders)
    public void processingOrders() {
        getOrders(AppContent.processing_orders);
        ordersAdapter.setAll(orders.getCurrent_orders());
    }

    @OnClick(R.id.ll_finished_orders)
    public void finishedOrders() {
        getOrders(AppContent.finished_orders);
        ordersAdapter.setAll(orders.getPrevious_orders());
    }

    @OnClick(R.id.btn_filter)
    public void filter() {
        filterOrders();
    }

    private void getOrders(String s) {
        tvFinishedOrders.setTextColor(Objects.requireNonNull(getActivity()).getColor(R.color.darkGray));
        tvProcessingOrders.setTextColor(Objects.requireNonNull(getActivity()).getColor(R.color.darkGray));
        vFOrderLine.setVisibility(View.INVISIBLE);
        vPOrderLine.setVisibility(View.INVISIBLE);
        switch (s) {
            case AppContent.processing_orders:
                tvProcessingOrders.setTextColor(Objects.requireNonNull(getActivity()).getColor(R.color.colorAccent));
                vPOrderLine.setVisibility(View.VISIBLE);
                break;
            case AppContent.finished_orders:
                tvFinishedOrders.setTextColor(Objects.requireNonNull(getActivity()).getColor(R.color.colorAccent));
                vFOrderLine.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void setData(Orders orders) {
        this.orders = orders;
        initSpinner(orders);
        initRecycle();
    }

    @Override
    public void showDialog(String s) {
        WaitDialogFragment.newInstance(s).show(getChildFragmentManager(), "");
    }

    @Override
    public void hideDialog() {
        WaitDialogFragment.newInstance("").dismiss();
    }

    private void filterOrders() {
        String type = (String) spOrderType.getSelectedItem();
        if (type.equals(getString(R.string.administrative))) {
            type = AppContent.ORDER_TYPE_MANAGEMENT;
        } else {
            type = AppContent.ORDER_TYPE_OFFICER;
        }

        if (spDate.getSelectedItem() == null) {
            presenter.getFilterOrders("delivery/ordersList");
        } else {
            long timestamp = (Long) spDate.getSelectedItem();
            //Log.e("testtotime", "date : " + timestamp);
            presenter.getFilterOrders("delivery/ordersList?date=" + timestamp + "&type=" + type);
        }
    }
}