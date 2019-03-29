package com.cindymb.airportapplication.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.cindymb.airportapplication.R;

import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

public class BaseFragment extends Fragment {

    private final String TAG = BaseFragment.class.getSimpleName();
    /**
     * NavController used by all start up screens
     * i.e Splash, Main and Details
     * extend BaseFragment for access
     */
    protected NavController mNavController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mNavController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
    }

    public void navigateToNextScreen(int aResId, Bundle aArguements, NavOptions aNavOptions) {
        mNavController.navigate(aResId, aArguements, aNavOptions);
    }
}
