package com.webapp.delavergy.utils.dialog.order;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.webapp.delavergy.R;
import com.webapp.delavergy.models.Order;
import com.webapp.delavergy.utils.AppContent;
import com.webapp.delavergy.utils.NavigateUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewOrderDialogFragment extends DialogFragment {

    @BindView(R.id.tv_receiver_address) TextView tvReceiverAddress;
    @BindView(R.id.tv_receiver_city) TextView tvReceiverCity;
    @BindView(R.id.tv_receiver_far) TextView tvReceiverFar;
    @BindView(R.id.tv_receiver_distance) TextView tvReceiverDistance;
    @BindView(R.id.tv_receiver_address_2) TextView tvReceiverAddress2;
    @BindView(R.id.tv_receiver_city_2) TextView tvReceiverCity2;
    @BindView(R.id.tv_receiver_far_2) TextView tvReceiverFar2;
    @BindView(R.id.tv_receiver_distance_2) TextView tvReceiverDistance2;
    @BindView(R.id.btn_accept_order) Button btnAcceptOrder;
    @BindView(R.id.btn_reject_order) Button btnRejectOrder;

    private int order_id;
    private NewOrderDialogFragmentListener listener;

    public static NewOrderDialogFragment newInstance(int order_id) {
        NewOrderDialogFragment fragment = new NewOrderDialogFragment();
        Bundle args = new Bundle();
        args.putInt(AppContent.ORDER_Id, order_id);
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
        View view = inflater.inflate(R.layout.dialog_fragment_new_order, container, false);
        ButterKnife.bind(this, view);
        if (getArguments() != null) {
            order_id = getArguments().getInt(AppContent.ORDER_Id);
            setData();
        }
        return view;
    }

    @Override
    public void onResume() {
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.95);
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getDialog().getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
        getDialog().getWindow().setAttributes((WindowManager.LayoutParams) params);
        super.onResume();
        getDialog().setOnKeyListener((dialog, keyCode, event) -> {
            if ((keyCode == android.view.KeyEvent.KEYCODE_BACK)) {
                dismiss();
                return true;
            } else return false;
        });
    }

    @OnClick(R.id.btn_reject_order)
    public void reject() {
        listener.reject();
        dismiss();
    }

    @OnClick(R.id.btn_accept_order)
    public void accept() {
        dismiss();
        NavigateUtils.openOrder(getContext(), order_id, true);
    }


    private void setData() {
        listener.setData(new Order(3, 122313, "منتهي", "طلب ضابط ارتباط"));
    }

    public void setListener(NewOrderDialogFragmentListener listener) {
        this.listener = listener;
    }

   public interface NewOrderDialogFragmentListener{
       void setData(Order order);
       void reject();
    }
}