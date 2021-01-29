package com.webapp.delavergy.utils.dialog;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.webapp.delavergy.R;
import com.webapp.delavergy.models.Result;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.listener.DialogView;
import com.webapp.delavergy.utils.listener.RequestListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BillDialogFragment extends BottomSheetDialogFragment implements DialogView<Boolean> {

    private static final String DELIVARY_COST_KEY = "cost";
    private static final String ORDER_ID_KEY = "id";

    @BindView(R.id.tv_value) TextView tvValue;
    @BindView(R.id.btn_attainment) Button tvAttainment;

    private RequestListener<Boolean> requestListener;

    public static BillDialogFragment newInstance(long id, double delivaryCost) {
        final BillDialogFragment fragment = new BillDialogFragment();
        final Bundle args = new Bundle();
        args.putDouble(DELIVARY_COST_KEY, delivaryCost);
        args.putLong(ORDER_ID_KEY, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_bill, container, false);
        ButterKnife.bind(this, view);
        tvValue.setText("" + getArguments().getDouble(DELIVARY_COST_KEY));
        return view;
    }

    @Override
    public void onResume() {
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        // Assign window properties to fill the parent
        params.width = getResources().getDisplayMetrics().widthPixels;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.transparent);
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        setCancelable(false);
        super.onResume();
        getDialog().setOnKeyListener((dialog, keyCode, event) -> {
            if ((keyCode == android.view.KeyEvent.KEYCODE_BACK)) {
                dismiss();
                return true;
            } else return false;
        });
    }

    public void setRequestListener(RequestListener<Boolean> requestListener) {
        this.requestListener = requestListener;
    }

    @OnClick(R.id.btn_attainment)
    public void attainment() {
        showDialog(getString(R.string.delivery_order));
        AppController.getInstance().getGetAPIData().getOrderData()
                .deliveredOrder(getActivity(), getArguments().getLong(ORDER_ID_KEY)
                        , new RequestListener<Result>() {
                            @Override
                            public void onSuccess(Result result, String msg) {
                                requestListener.onSuccess(true, "");
                                hideDialog();
                                dismiss();
                            }

                            @Override
                            public void onFail(String msg) {
                                requestListener.onFail(msg);
                                hideDialog();
                                dismiss();
                            }
                        });
    }

    @Override
    public void setData(Boolean aBoolean) {

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