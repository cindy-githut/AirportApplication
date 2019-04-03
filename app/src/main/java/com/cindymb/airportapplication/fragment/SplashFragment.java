package com.cindymb.airportapplication.fragment;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cindymb.airportapplication.R;
import com.cindymb.airportapplication.base.BaseFragment;
import com.cindymb.airportapplication.databinding.FragmentSplashBinding;
import com.cindymb.airportapplication.di.MyViewModelFactory;
import com.cindymb.airportapplication.utils.Constant;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import androidx.navigation.NavOptions;
import pub.devrel.easypermissions.EasyPermissions;


public class SplashFragment extends BaseFragment {

    @Inject
    MyViewModelFactory mFactory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentSplashBinding mFragmentSplashBinding = FragmentSplashBinding.inflate(inflater, container, false);
        mFragmentSplashBinding.splashProgressBar.setProgress(10);

        return mFragmentSplashBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (EasyPermissions.hasPermissions(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                navigateToNextScreen();
            } else {
                EasyPermissions.requestPermissions(this, getString(R.string.msg_permission_request), Constant.PERMISSION_CODE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
            }
        } else {
            navigateToNextScreen();
        }
    }

    private void navigateToNextScreen() {
        if (isConnected(requireActivity())) {
            navigateToNextScreen(R.id.action_splashFragment_to_mapsFragment, new NavOptions.Builder().setPopUpTo(R.id.splashFragment, true).build());
        } else {
            displayDialog(getString(R.string.msg_connectionError));
        }
    }
}
