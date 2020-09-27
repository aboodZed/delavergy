package com.webapp.delavergy.feature.order;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.webapp.delavergy.R;
import com.webapp.delavergy.utils.dialog.BillDialogFragment;
import com.webapp.delavergy.utils.language.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderActivity extends BaseActivity {

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
    @BindView(R.id.tv_delivery_value) TextView tvDeliveryValue;
    @BindView(R.id.tv_app_deserved) TextView tvAppDeserved;
    @BindView(R.id.tv_captain_dues) TextView tvCaptainDues;
    @BindView(R.id.btn_delivered) Button btnDelivered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
    }

    @OnClick(R.id.btn_delivered)
    public void delivered() {
        BillDialogFragment.newInstance().show(getSupportFragmentManager(), "");
    }

    @Override
    public void navigate(int page) {

    }
}