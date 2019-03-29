package com.cindymb.airportapplication;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cindymb.airportapplication.base.BaseFragment;
import com.cindymb.airportapplication.databinding.FragmentSplashBinding;

import org.jetbrains.annotations.NotNull;

import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import pub.devrel.easypermissions.EasyPermissions;


public class SplashFragment extends BaseFragment {
    private FragmentSplashBinding mFragmentSplashBinding;

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mFragmentSplashBinding = FragmentSplashBinding.inflate(inflater, container, false);
        mFragmentSplashBinding.splashProgressBar.setProgress(10);
        //navigateToNextScreen(R.id.action_splashFragment_to_departureFragment, new NavOptions.Builder().setPopUpTo(R.id.splashFragment, true).build());
        mNavController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        mNavController.navigate(R.id.action_splashFragment_to_mapsFragment, null,  new NavOptions.Builder().setPopUpTo(R.id.splashFragment, true).build());

        return mFragmentSplashBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (EasyPermissions.hasPermissions(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                //get currentLocation and load airports
            } else {
                EasyPermissions.requestPermissions(this, getString(R.string.msg_permission_request), Constant.PERMISSION_CODE,  Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
            }
        } else {
            //get currentLocation and load airports
        }
    }
}
