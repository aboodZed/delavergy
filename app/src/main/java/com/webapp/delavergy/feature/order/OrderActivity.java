package com.webapp.delavergy.feature.order;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.webapp.delavergy.R;
import com.webapp.delavergy.models.Order;
import com.webapp.delavergy.utils.AppContent;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.NavigateUtils;
import com.webapp.delavergy.utils.PermissionUtil;
import com.webapp.delavergy.utils.UIUtils;
import com.webapp.delavergy.utils.dialog.BillDialogFragment;
import com.webapp.delavergy.utils.dialog.WaitDialogFragment;
import com.webapp.delavergy.utils.dialog.notification.NotificationFragment;
import com.webapp.delavergy.utils.language.BaseActivity;
import com.webapp.delavergy.utils.listener.DialogView;
import com.webapp.delavergy.utils.listener.RequestListener;

import butterknife.BindView;
import butterknife.OnClick;

import static com.webapp.delavergy.utils.AppContent.PHONE_CALL_CODE;

public class OrderActivity extends BaseActivity implements DialogView<Order> {

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
    @BindView(R.id.address_text) TextView addressText;
    @BindView(R.id.tv_sender_address_text) TextView tvSenderAddressText;
    @BindView(R.id.tv_receiver_address_text) TextView tvReceiverAddressText;
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
        orderPresenter = new OrderPresenter(this, this);
        if (getIntent().getExtras() != null) {
            long id = getIntent().getExtras().getLong(AppContent.ORDER_Id);
            orderPresenter.getOrderData(id);
        }
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
        BillDialogFragment billDialogFragment = BillDialogFragment
                .newInstance(order.getId(), order.getDelivery_cost());
        billDialogFragment.show(getSupportFragmentManager(), "");
        billDialogFragment.setRequestListener(new RequestListener<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean, String msg) {
                if (aBoolean) {
                    llBalance.setVisibility(View.VISIBLE);
                    btnDelivered.setVisibility(View.GONE);
                    AppController.getInstance().getAppLocal().setUserStatus(AppContent.DRIVER_STATUS_ONLINE);
                }
            }

            @Override
            public void onFail(String msg) {
                UIUtils.showLongToast(OrderActivity.this, msg);
            }
        });
    }

    @Override
    public void navigate(int page) {

    }

    @Override
    public void setData(Order order) {
        this.order = order;
        tvOrderId.setText(getString(R.string.invoice_number) + " : #" + order.getInvoice_number());
        tvSenderName.setText(order.getSender_name() + "");
        tvSenderAddress.setText(order.getFrom_city().getName() + " , " + order.getFrom_state().getName());
        tvReceiverName.setText(order.getReceiver_name() + "");
        tvReceiverAddress.setText(order.getTo_city().getName() + " , " + order.getTo_state().getName());
        tvSenderAddressText.setText(order.getSender_address() + "");
        tvReceiverAddressText.setText(order.getReceiver_address() + "");

        if (order.getDetails() != null)
            tvOrderDetails.append(order.getDetails());

        tvDeliveryValue.setText(order.getDelivery_cost() + "");
        tvAppDeserved.setText(order.getApp_revenue() + "");
        tvCaptainDues.setText(order.getDriver_revenue() + "");

        if (order.getType().equals(AppContent.ORDER_TYPE_OFFICER)) {
            addressText.setText(getString(R.string.store_address));
        }

        if (order.getStatus().equals(AppContent.ORDER_STATUS_COMPLETE)) {
            llBalance.setVisibility(View.VISIBLE);
            btnDelivered.setVisibility(View.GONE);
        }
    }

    @Override
    public void showDialog(String s) {
        WaitDialogFragment.newInstance(s).show(getSupportFragmentManager(), "");
    }

    @Override
    public void hideDialog() {
        WaitDialogFragment.newInstance("").dismiss();
    }

    @OnClick(R.id.iv_sender_location)
    public void senderLocation() {
        NavigateUtils.setLocation(this, new LatLng(order.getFrom_state().getLat(), order.getFrom_state().getLng()));
    }

    @OnClick(R.id.iv_receiver_location)
    public void receiverLocation() {
        NavigateUtils.setLocation(this, new LatLng(order.getTo_state().getLat(), order.getTo_state().getLng()));
    }

    @OnClick(R.id.iv_sender_call)
    public void senderCall() {
        if (PermissionUtil.isPermissionGranted(Manifest.permission.CALL_PHONE, this)) {
            if (order.getSender_mobile() != null)
                NavigateUtils.makeCall(this, order.getSender_mobile());
        } else {
            PermissionUtil.requestPermission(this, Manifest.permission.CALL_PHONE, PHONE_CALL_CODE);
        }
    }

    @OnClick(R.id.iv_receiver_call)
    public void receiverCall() {
        if (PermissionUtil.isPermissionGranted(Manifest.permission.CALL_PHONE, this)) {
            if (order.getReceiver_mobile() != null)
                NavigateUtils.makeCall(this, order.getReceiver_mobile());
        } else {
            PermissionUtil.requestPermission(this, Manifest.permission.CALL_PHONE, PHONE_CALL_CODE);
        }
    }
}