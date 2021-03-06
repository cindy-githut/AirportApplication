package com.cindymb.airportapplication.ui.base;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.widget.Button;

import com.cindymb.airportapplication.R;
import com.cindymb.airportapplication.ui.utils.LoggingHelper;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;

import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import dagger.android.support.AndroidSupportInjection;

public class BaseFragment extends Fragment {

    public final LatLng GAUTENG_COORDINATES = new LatLng(-26.0123951, 27.0061092);
    /**
     * NavController used by all start up screens
     * i.e Splash, Main and Details
     * extend BaseFragment for access
     */
    protected NavController mNavController;

    /**
     * @param drawable drawable to use a mp marker
     * @return Bitmap descriptor of icon
     */
    public static BitmapDescriptor getMarkerIconFromDrawable(Drawable drawable) {
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    /**
     * Checks for Network & Internet Connectivity
     *
     * @param mActivity current context
     * @return true if internet available
     */
    public static boolean isConnected(Context mActivity) {
        try {
            ConnectivityManager cm = (ConnectivityManager) mActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            AndroidSupportInjection.inject(this);
        } catch (IllegalArgumentException aE) {
            LoggingHelper.error(BaseFragment.class, "Please add " + this.getClass().getSimpleName() + " to FragmentModule.class:\nError: " + aE.getMessage());
        }
        super.onCreate(savedInstanceState);
        mNavController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

    }

    public void navigateToNextScreen(int aResId, NavOptions aNavOptions) {
        mNavController.navigate(aResId, null, aNavOptions);
    }

    public void navigateToNextScreenWithArguments(NavDirections aNavDirections) {
        mNavController.navigate(aNavDirections);
    }

    public void displayDialog(String aMessage) {
        if (!isAdded()) return;
        displayDialog(aMessage, getString(R.string.lbl_ok), null, true);
    }

    /**
     * Method to display a dialog with both positive and negative actions
     *
     * @param aMessage         - Message to display
     * @param aPositiveText    - Positive Text eg YES / Okay
     * @param aPositiveOnClick - Positive on click action
     */
    public void displayDialog(String aMessage, String aPositiveText, DialogInterface.OnClickListener aPositiveOnClick, boolean cancellable) {
        if (!isAdded()) return;
        if (TextUtils.isEmpty(aMessage)) {
            aMessage = getString(R.string.msg_an_error_occured);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext(), R.style.AlertDialogCustom);
        builder.setMessage(aMessage);
        if (!TextUtils.isEmpty(aPositiveText)) {
            builder.setPositiveButton(aPositiveText, aPositiveOnClick);
        }
        builder.setCancelable(cancellable);
        AlertDialog alert = builder.create();
        alert.show();
        Button positiveButton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        positiveButton.setTextColor(Color.BLACK);
        Button negativeButton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        negativeButton.setTextColor(Color.BLACK);
    }
}
