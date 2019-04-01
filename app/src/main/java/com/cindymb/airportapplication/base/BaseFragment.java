package com.cindymb.airportapplication.base;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.widget.Button;

import com.cindymb.airportapplication.R;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

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

    public void navigateToNextScreen(int aResId, NavOptions aNavOptions) {
        if(mNavController != null){
            mNavController.navigate(aResId, null, aNavOptions);
        }

    }

    public void displayDialog(String aMessage) {
        if (!isAdded()) return;
        displayDialog(aMessage, getString(R.string.lbl_ok), null, null, null, true);
    }
    /**
     * Method to display a dialog with both positive and negative actions
     *
     * @param aMessage         - Message to display
     * @param aPositiveText    - Positive Text eg YES / Okay
     * @param aPositiveOnClick - Positive on click action
     * @param aNegativeText    - Negative text eg NO / Cancel
     * @param aNegativeOnClick - Negative on click action
     */
    public void displayDialog(String aMessage, String aPositiveText, DialogInterface.OnClickListener aPositiveOnClick, String aNegativeText, DialogInterface.OnClickListener aNegativeOnClick, boolean cancellable) {
        if (!isAdded()) return;
        if (TextUtils.isEmpty(aMessage)) {
            aMessage = getString(R.string.lbl_an_error_occured);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext(), R.style.AlertDialogCustom);
        builder.setMessage(aMessage);
        if (!TextUtils.isEmpty(aNegativeText)) {
            if (aNegativeOnClick == null) {
                aNegativeOnClick = (dialog, which) -> {
                    // Do nothing
                };
            }
            builder.setNegativeButton(aNegativeText, aNegativeOnClick);
        }
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
}
