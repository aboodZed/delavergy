package com.webapp.delavergy.feature.order;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.webapp.delavergy.R;
import com.webapp.delavergy.models.Order;
import com.webapp.delavergy.utils.AppContent;
import com.webapp.delavergy.utils.dialog.BillDialogFragment;
import com.webapp.delavergy.utils.dialog.notification.NotificationFragment;
import com.webapp.delavergy.utils.language.BaseActivity;
import com.webapp.delavergy.utils.listener.RequestListener;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderActivity extends BaseActivity {

    @BindView(R.id.iv_back) ImageView ivBack;
    @BindView(R.id.iv_notification) ImageView ivNotification;
    @BindView(R.id.tv_order_id) TextView tvOrderId;
    @BindView(R.id.tv_sender_name) TextView tvSenderName;
    @BindView(R.id.tv_sender_address) TextView tvSenderAddress;
    @BindView(R.id.iv_sender_call) ImageView ivSenderCall;
    @BindView(R.id.iv_sender_location) ImageView ivSenderLocation;
    @BindView(R.id.tv_receiver_name) TextView tvReceiverName;
    @BindView(R.id.tv_receiver_address) TextView tvReceiverAddress;
    @BindView(R.id.iv_receiver_call) ImageView ivReceiverCall;
    @BindView(R.id.iv_receiver_location) ImageView ivReceiverLocation;
    @BindView(R.id.tv_receiver_address_text) TextView tvReceiverAddressText;
    @BindView(R.id.tv_receiver_address_text_2) TextView tvReceiverAddressText2;
    @BindView(R.id.tv_order_details) TextView tvOrderDetails;
    @BindView(R.id.ll_balance) LinearLayout llBalance;
    @BindView(R.id.tv_delivery_value) TextView tvDeliveryValue;
    @BindView(R.id.tv_app_deserved) TextView tvAppDeserved;
    @BindView(R.id.tv_captain_dues) TextView tvCaptainDues;
    @BindView(R.id.btn_delivered) Button btnDelivered;

    private OrderPresenter orderPresenter;
    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.setLayoutRes(R.layout.activity_order);
        super.onCreate(savedInstanceState);
        if (getIntent().getExtras() != null) {
            long id = getIntent().getExtras().getLong(AppContent.ORDER_Id);
        }
        orderPresenter = new OrderPresenter(this);
    }

    @OnClick(R.id.iv_back)
    public void back() {
        onBackPressed();
    }

    @OnClick(R.id.iv_notification)
    public void OpeNotification() {
        NotificationFragment.newInstance().show(getSupportFragmentManager(), "");
    }

    @OnClick(R.id.btn_delivered)
    public void delivered() {
        BillDialogFragment billDialogFragment = BillDialogFragment.newInstance();
        billDialogFragment.show(getSupportFragmentManager(), "");
        billDialogFragment.setRequestListener(new RequestListener<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean, String msg) {
                if (aBoolean) {
                    llBalance.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void navigate(int page) {

    }
}