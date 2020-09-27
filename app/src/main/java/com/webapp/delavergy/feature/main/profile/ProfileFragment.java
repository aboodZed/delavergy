package com.webapp.delavergy.feature.main.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.webapp.delavergy.R;

import butterknife.ButterKnife;

public class ProfileFragment extends Fragment {

    public static final int page = 205;

    private ProfilePresenter profilePresenter;

    private ProfileFragment() {}

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
        profilePresenter = new ProfilePresenter(getActivity());
        return view;
    }
}