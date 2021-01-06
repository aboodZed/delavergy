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
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.webapp.delavergy.R;
import com.webapp.delavergy.models.Result;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.ToolUtils;
import com.webapp.delavergy.utils.listener.DialogView;
import com.webapp.delavergy.utils.listener.RequestListener;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePasswordDialog extends DialogFragment implements DialogView<Result> {

    @BindView(R.id.et_old_password) EditText etOldPassword;
    @BindView(R.id.et_new_password) EditText etNewPassword;
    @BindView(R.id.et_config_new_password) EditText etConfigNewPassword;
    @BindView(R.id.btn_cancel) Button btnCancel;
    @BindView(R.id.btn_change) Button btnChange;

    public static ChangePasswordDialog newInstance() {
        return new ChangePasswordDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_change_password, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.8);
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.transparent);
        getDialog().getWindow().setAttributes((WindowManager.LayoutParams) params);
        setCancelable(false);
        super.onResume();
        getDialog().setOnKeyListener((dialog, keyCode, event) -> {
            if ((keyCode == KeyEvent.KEYCODE_BACK)) {
                this.dismiss();
                return true;
            } else return false;
        });
    }

    @OnClick(R.id.btn_cancel)
    public void close() {
        dismiss();
    }

    @OnClick(R.id.btn_change)
    public void change() {
        validateInput();
    }

    private void validateInput() {

        String old = etOldPassword.getText().toString().trim();
        String ne = etNewPassword.getText().toString().trim();
        String con = etConfigNewPassword.getText().toString().trim();

        if (TextUtils.isEmpty(old)) {
            etOldPassword.setError(getString(R.string.required_field));
            return;
        }

        if (old.length() < 6) {
            etOldPassword.setError(getString(R.string.six_char));
            return;
        }

        if (!old.equals(AppController.getInstance().getAppLocal().getUser().getUser().getPassword())) {
            etOldPassword.setError(getString(R.string.not_match));
            return;
        }

        if (TextUtils.isEmpty(ne)) {
            etNewPassword.setError(getString(R.string.required_field));
            return;
        }

        if (ne.length() < 6) {
            etNewPassword.setError(getString(R.string.six_char));
            return;
        }

        if (TextUtils.isEmpty(con)) {
            etConfigNewPassword.setError(getString(R.string.required_field));
            return;
        }

        if (con.length() < 6) {
            etConfigNewPassword.setError(getString(R.string.six_char));
            return;
        }

        if (!con.equals(ne)) {
            etConfigNewPassword.setError(getString(R.string.not_match));
            return;
        }

        resetPassword(ne);
    }

    private void resetPassword(String ne) {
        showDialog(getString(R.string.reset_password_string));
        AppController.getInstance().getGetAPIData().getPasswordData()
                .resetPassword(getActivity(), ne, new RequestListener<Result>() {
                    @Override
                    public void onSuccess(Result result, String msg) {
                        hideDialog();
                        ToolUtils.showLongToast(getActivity(), result.getMessage());
                        dismiss();
                    }

                    @Override
                    public void onFail(String msg) {
                        hideDialog();
                        ToolUtils.showLongToast(getActivity(), msg);
                    }
                });
    }

    @Override
    public void setData(Result result) {

    }

    @Override
    public void showDialog(String s) {
        WaitDialogFragment.newInstance(s).show(getFragmentManager(), "");
    }

    @Override
    public void hideDialog() {
        WaitDialogFragment.newInstance("").dismiss();
    }
}
