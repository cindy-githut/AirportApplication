package com.cindymb.airportapplication.ui;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cindymb.airportapplication.R;
import com.cindymb.airportapplication.ui.base.BaseFragment;
import com.cindymb.airportapplication.databinding.FragmentSplashBinding;
import com.cindymb.airportapplication.di.MyViewModelFactory;
import com.cindymb.airportapplication.ui.utils.Constant;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import androidx.navigation.NavOptions;
import pub.devrel.easypermissions.EasyPermissions;


public class SplashFragment extends BaseFragment {

    @Inject
    MyViewModelFactory mFactory;
    FragmentSplashBinding mFragmentSplashBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mFragmentSplashBinding = FragmentSplashBinding.inflate(inflater, container, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (EasyPermissions.hasPermissions(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                navigateToNextScreen();
            } else {
                EasyPermissions.requestPermissions(this, getString(R.string.msg_permission_request), Constant.PERMISSION_CODE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
            }
        } else {
            navigateToNextScreen();
        }

        return mFragmentSplashBinding.getRoot();
    }

    private void navigateToNextScreen() {
        if (isConnected(requireActivity())) {
            navigateToNextScreen(R.id.action_splashFragment_to_mapsFragment, new NavOptions.Builder().setPopUpTo(R.id.splashFragment, true).build());
        } else {
            displayDialog(getString(R.string.msg_connectionError));
        }
    }
}
