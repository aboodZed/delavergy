package com.webapp.delavergy.utils.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.webapp.delavergy.R;
import com.webapp.delavergy.models.Result;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.UIUtils;
import com.webapp.delavergy.utils.listener.RequestListener;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendMessageDialog extends DialogFragment {

    @BindView(R.id.et_subject) EditText etEnterSubject;
    @BindView(R.id.et_message) EditText etEnterMessage;
    @BindView(R.id.btn_send) Button btnSend;
    @BindView(R.id.btn_cancel) Button btnCancel;

    public static SendMessageDialog newInstance() {
        SendMessageDialog fragment = new SendMessageDialog();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_send_message, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.transparent);
        getDialog().getWindow().setAttributes((WindowManager.LayoutParams) params);
        setCancelable(false);
        super.onResume();
        getDialog().setOnKeyListener((dialog, keyCode, event) -> {
            if ((keyCode == KeyEvent.KEYCODE_BACK)) {
                SendMessageDialog.this.dismiss();
                return true;
            } else return false;
        });
    }

    @OnClick(R.id.btn_cancel)
    public void cancel() {
        dismiss();
    }

    @OnClick(R.id.btn_send)
    public void sendMessage() {
        String subject = etEnterSubject.getText().toString().trim();
        String message = etEnterMessage.getText().toString().trim();
        HashMap<String, String> map = new HashMap<>();
        /*map.put("name", AppController.getInstance().getAppLocal().getUser().getUser().getName());
        map.put("email", AppController.getInstance().getAppLocal().getUser().getUser().getEmail());
        map.put("mobile", AppController.getInstance().getAppLocal().getUser().getUser().getMobile());*/

        if (TextUtils.isEmpty(subject)) {
            etEnterSubject.setError(getString(R.string.required_field));
            return;
        } else {
            map.put("subject", subject);
        }
        if (TextUtils.isEmpty(message)) {
            etEnterMessage.setError(getString(R.string.required_field));
            return;
        } else {
            map.put("message", message);
        }
        send(map);
    }

    private void send(HashMap<String, String> map) {
        WaitDialogFragment.newInstance(getString(R.string.send_message)).show(getChildFragmentManager(),"");

        AppController.getInstance().getGetAPIData().getSettingData().contact(getActivity(), map,
                new RequestListener<Result>() {
                    @Override
                    public void onSuccess(Result result, String msg) {
                        UIUtils.showLongToast(getActivity(), result.getMessage());
                        dismiss();
                        WaitDialogFragment.newInstance("").dismiss();
                    }

                    @Override
                    public void onFail(String msg) {
                        UIUtils.showLongToast(getActivity(), msg);
                        dismiss();
                        WaitDialogFragment.newInstance("").dismiss();
                    }
                });
    }
}
