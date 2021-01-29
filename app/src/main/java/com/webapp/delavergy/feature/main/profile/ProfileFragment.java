package com.webapp.delavergy.feature.main.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.webapp.delavergy.R;
import com.webapp.delavergy.models.LoginResult;
import com.webapp.delavergy.models.User;
import com.webapp.delavergy.utils.AppController;
import com.webapp.delavergy.utils.ImageUtils;
import com.webapp.delavergy.utils.UIUtils;
import com.webapp.delavergy.utils.dialog.ChangePasswordDialog;
import com.webapp.delavergy.utils.dialog.WaitDialogFragment;
import com.webapp.delavergy.utils.listener.DialogView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment implements DialogView<User> {

    public static final int page = 205;

    @BindView(R.id.iv_avatar) CircleImageView ivAvatar;
    @BindView(R.id.pb_avatar) ProgressBar pbAvatar;
    @BindView(R.id.iv_id) CircleImageView ivId;
    @BindView(R.id.iv_vehicle_img) CircleImageView ivVehicleImg;
    @BindView(R.id.iv_vehicle_license) CircleImageView ivVehicleLicense;
    @BindView(R.id.iv_driving_license) CircleImageView ivDrivingLicense;
    @BindView(R.id.iv_insurance_img) CircleImageView ivInsuranceImg;
    @BindView(R.id.iv_credit_note) CircleImageView ivCreditNote;
    @BindView(R.id.et_full_name) EditText etFullName;
    @BindView(R.id.et_phone) EditText etPhone;
    @BindView(R.id.et_plate_number) EditText etPlateNumber;
    @BindView(R.id.et_car_type) EditText etCarType;
    @BindView(R.id.et_password) EditText etPassword;
    @BindView(R.id.et_finished_license) EditText etFinishedLicense;
    @BindView(R.id.et_city) EditText etCity;
    @BindView(R.id.et_neighborhood) EditText etNeighborhood;
    @BindView(R.id.btn_edit_password) Button btnEditPassword;

    private ProfilePresenter profilePresenter;

    private ProfileFragment() {
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        //ButterKnife
        ButterKnife.bind(this, view);
        //presenter
        profilePresenter = new ProfilePresenter(getActivity(), this);
        return view;
    }

    @OnClick(R.id.btn_edit_password)
    public void changePassword() {
        ChangePasswordDialog.newInstance().show(getChildFragmentManager(), "");
    }

    @Override
    public void setData(User user) {
        //update User
        LoginResult lr = AppController.getInstance().getAppLocal().getUser();
        lr.setUser(user);
        AppController.getInstance().getAppLocal().setUser(lr);

        ImageUtils.loadImageNormal(getActivity(), pbAvatar, user.getAvatar_url(), ivAvatar);
        ImageUtils.loadImageNormal(getActivity(), null, user.getId_pic_url(), ivId);
        ImageUtils.loadImageNormal(getActivity(), null, user.getVehicle_pic_url(), ivVehicleImg);
        ImageUtils.loadImageNormal(getActivity(), null, user.getVehicle_license_url(), ivVehicleLicense);
        ImageUtils.loadImageNormal(getActivity(), null, user.getDrive_license_url(), ivDrivingLicense);
        ImageUtils.loadImageNormal(getActivity(), null, user.getInsurance_license_url(), ivInsuranceImg);
        //UIUtils.loadImageNormal(getActivity(), null, user.getAvatar_url(), ivCreditNote);

        etFullName.setText(user.getName());
        etPhone.setText(user.getMobile());
        etPlateNumber.setText(user.getVehicle_plate());
        etCarType.setText(user.getVehicle_type());
        etPassword.setText(user.getPassword());
        //etFinishedLicense.setText(user.getName());
        //etCity.setText(user.getName());
        //etNeighborhood.setText(user.getName());
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